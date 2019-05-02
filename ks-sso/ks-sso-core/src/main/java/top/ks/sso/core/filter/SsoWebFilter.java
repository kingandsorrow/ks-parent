package top.ks.sso.core.filter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import top.ks.common.constant.Const;
import top.ks.common.user.SsoUser;
import top.ks.common.util.ResponseEntity;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.sso.api.LoginServiceI;
import top.ks.sso.api.req.SsoUserReq;
import top.ks.sso.api.resp.SsoUserResp;
import top.ks.sso.core.resp.SsoResp;
import top.ks.sso.core.util.CookieUtil;
import top.ks.sso.core.util.HttpUtil;
import top.ks.sso.core.util.SsoWebLoginHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static top.ks.common.enums.ResultStatus.USER_NOT_LOGIN;

/**
 * <b>类名称:</b>SsoWebFilter$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/1/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/1/20
 */
@Component
public class SsoWebFilter extends HttpServlet implements Filter {
    private static final Log log = LogFactory.getLog(SsoWebFilter.class);

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private String ssoServer;
    private String logoutPath;
    private String excludedPaths;
    @Reference(version = "${dubbo.service.version}")
    private LoginServiceI loginServiceI;

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
        res.setHeader("Access-Control-Allow-Origin", "http://www.ks.com:8050");
        // 允许的访问方法
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "token,Origin,Authorization, X-Requested-With, Content-Type, Accept");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        String servletPath = req.getServletPath();
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
        // logout path check
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
        if (Strings.isNotEmpty(cookieToken) && cookieToken.equals(sessionToken) && ssoUser != null) {
            log.info(LogFormat.formatMsg("SsoWebFilter.doFilter", "cookieToken check success..", ""));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ssoUserReq.setToken(cookieToken);
        SsoUserResp ssoUserResp = loginServiceI.getUserByToken(ssoUserReq);
        // valid login fail
        if (!ssoUserResp.respSuc()) {
            String header = req.getHeader("Content-Type");
            boolean isJson = header != null && header.contains("json");
//            if (isJson) {
            // json msg
            res.setContentType("application/json;charset=utf-8");
            SsoResp ssoResp = new SsoResp(USER_NOT_LOGIN.getCode(), USER_NOT_LOGIN.getMessage());
            ssoResp.setLoginUrl(ssoServer);
            res.getWriter().println(JSON.toJSON(ssoResp));
            return;
            /*} else {
                // total link
                String link = req.getRequestURL().toString();
                // redirect logout
                String loginPageUrl = ssoServer.concat(Const.SSO_LOGIN)
                        + "?" + Const.REDIRECT_URL + "=" + link;
                res.sendRedirect(loginPageUrl);
                return;
            }*/
        }
        // ser sso user
        servletRequest.setAttribute(Const.SSO_USER, ssoUserResp.getSsoUser());
        // already login, allow
        filterChain.doFilter(servletRequest, servletResponse);
        return;
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
