package top.ks.sso.core.filter;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import top.ks.common.constant.Const;
import top.ks.common.enums.ResultStatus;
import top.ks.common.user.SsoUser;
import top.ks.common.util.LogFormat;
import top.ks.common.util.SpringHelper;
import top.ks.common.util.Strings;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.req.SsoUserReq;
import top.ks.sso.consumer.resp.SsoUserResp;
import top.ks.sso.core.resp.SsoResp;
import top.ks.sso.core.util.HttpUtil;
import top.ks.sso.core.util.SsoWebLoginHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static top.ks.common.enums.ResultStatus.LOGINSERVICE_NOT_FOUND;
import static top.ks.common.enums.ResultStatus.USER_NOT_LOGIN;

/**
 * <b>类名称:</b>SsoWebFilter$<br/>
 * <b>类注释:</b>统一登录拦截Filter<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/1/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/1/20
 */
@Component
public class SsoWebFilter extends HttpServlet implements Filter {
    private static final Log log = LogFactory.getLog(SsoWebFilter.class);

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private String ssoServer;
    private String logoutPath;
    private String excludedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ssoServer = filterConfig.getInitParameter(Const.SSO_SERVER);
        logoutPath = filterConfig.getInitParameter(Const.SSO_LOGOUT_PATH);
        excludedPaths = filterConfig.getInitParameter(Const.SSO_EXCLUDED_PATHS);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        //1.预处理res 保证跨域调用（本地调用）
        preHandelRes(res);
        //获取当前路径
        String servletPath = req.getServletPath();
        //2.不需要校验登录的路径,放行。
        if (Strings.isNotEmpty(excludedPaths)) {
            for (String excludedPath : excludedPaths.split(",")) {
                String uriPattern = excludedPath.trim();
                // 支持ANT表达式
                if (antPathMatcher.match(uriPattern, servletPath)) {
                    // excluded path, allow
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        //3.退出登录处理
        if (Strings.isNotEmpty(logoutPath) && logoutPath.equals(servletPath)) {
            // remove cookie
            SsoWebLoginHelper.removeSessionIdByCookie(req, res);
            // redirect logout
            String logoutPageUrl = ssoServer.concat(Const.SSO_LOGOUT);
            res.sendRedirect(logoutPageUrl);
            return;
        }
        // valid login user, cookie + redirect
        SsoUserReq ssoUserReq = new SsoUserReq();
        //String cookieToken = CookieUtil.getValue(req, Const.SSO_TOKEN);
        String cookieToken = getCookieToken(req);
        String sessionToken = (String) req.getSession().getAttribute(Const.SSO_TOKEN);
        SsoUser ssoUser = (SsoUser) req.getSession().getAttribute(Const.SSO_USER);
        //4.校验session登录，如果session中存在则放行
        if (Strings.isNotEmpty(cookieToken) && cookieToken.equals(sessionToken) && ssoUser != null) {
            log.info(LogFormat.formatMsg("SsoWebFilter.doFilter", "cookieToken check success..", ""));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ssoUserReq.setToken(cookieToken);
        LoginServiceI loginServiceI = null;
        try {
            loginServiceI = getLoginServiceI(cookieToken);
        } catch (ClassNotFoundException e) {
            log.error(LogFormat.formatMsg("SsoWebFilter.doFilter", "get login ServiceI error..", e));
            resSSOJson(res, LOGINSERVICE_NOT_FOUND);
            return;
        }
        if (loginServiceI == null) {
            resSSOJson(res, LOGINSERVICE_NOT_FOUND);
            return;
        }
        SsoUserResp ssoUserResp = loginServiceI.getUserByToken(ssoUserReq);
        // valid login fail
        if (!ssoUserResp.respSuc()) {
            String header = req.getHeader("Content-Type");
            boolean isJson = header != null && header.contains("json");
//            if (isJson) {
            // json msg
            resSSOJson(res, USER_NOT_LOGIN);
            return;
        }
        // ser sso user
        servletRequest.setAttribute(Const.SSO_USER, ssoUserResp.getSsoUser());
        // already login, allow
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    private void resSSOJson(HttpServletResponse res, ResultStatus resultStatus) throws IOException {
        res.setContentType("application/json;charset=utf-8");
        SsoResp ssoResp = new SsoResp(resultStatus);
        ssoResp.setLoginUrl(ssoServer);
        res.getWriter().println(JSON.toJSON(ssoResp));
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获取登录接口（Oss Client）
     * @author : birjc
     * @CreateDate : 2019-12-01 21:41
     */
    private LoginServiceI getLoginServiceI(String cookieToken) throws ClassNotFoundException {

        ApplicationConfig application = new ApplicationConfig();
        application.setName("ks-sso-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ReferenceConfig<LoginServiceI> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(application);
        referenceConfig.setRegistry(registry);
        referenceConfig.setGroup("oss_login");
        referenceConfig.setInterface(LoginServiceI.class);
        referenceConfig.setVersion("1.0.0");
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        return cache.get(referenceConfig);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 预处理 res
     * @author : birjc
     * @CreateDate : 2019-12-01 21:35
     */
    private void preHandelRes(HttpServletResponse res) {
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        // 允许的访问方法
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "token,Origin,Authorization, X-Requested-With, Content-Type, Accept");
        res.setHeader("Access-Control-Allow-Credentials", "true");
    }

    private String getToken(HttpServletRequest req) {
        Map<String, String> headersMap = HttpUtil.getHeadersInfo(req);
        return headersMap.get(Const.TOKEN);
    }

    private String getCookieToken(HttpServletRequest req) {
        Map<String, String> headersMap = HttpUtil.getHeadersInfo(req);
        String cookie = headersMap.get("cookie");
        if (Strings.isEmpty(cookie)) {
            log.info(LogFormat.formatMsg("SsoWebFilter.getCookieToken", "get header cookie is null..", ""));
            return null;
        }
        String[] arr = cookie.split(";");
        if (arr == null) {
            log.info(LogFormat.formatMsg("SsoWebFilter.getCookieToken", "get header cookie is null..", ""));
            return null;
        }
        for (String arrStr : arr) {
            if (arrStr.indexOf(Const.TOKEN) >= 0) {
                return arrStr.split("=")[1];
            }
        }
        return null;
    }
}
