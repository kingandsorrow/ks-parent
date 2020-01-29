package top.ks.oss.web.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.common.util.Strings;
import top.ks.oss.web.util.HttpUtil;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.req.SsoUserReq;
import top.ks.sso.consumer.resp.SsoUserResp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static top.ks.common.enums.ResultStatus.LOGIN_EXPIRE;

/**
 * <b>类名称:</b>LoginInterceptor$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/6<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright KS 2018/10/6
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(LoginInterceptor.class);
    @Reference(version = "${dubbo.service.version}", group = "${dubbo.group}", interfaceName = "ossLoginServiceI")
    private LoginServiceI loginServiceI;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*Map<String, String> headersMap = HttpUtil.getHeadersInfo(request);
        String token = headersMap.get("token");
        if (Strings.isEmpty(token)) {
            log.info(LogFormat.formatMsg("LoginInterceptor.preHandle", "not get token by Authorization", ""));
            setInvalidResp(response);
            return false;
        }
        SsoUserReq ssoUserReq = new SsoUserReq();
        ssoUserReq.setToken(token);
        SsoUserResp ssoUserResp = loginServiceI.getUserByToken(ssoUserReq);
        if (!ssoUserResp.respSuc()) {
            log.info(LogFormat.formatMsg("LoginInterceptor.preHandle", "checkTokenResp is::" + JSON.toJSONString(ssoUserResp), ""));
            setInvalidResp(response);
            return false;
        }*/
        return true;
    }

    private void setInvalidResp(HttpServletResponse response) throws IOException {
        ResponseEntity responseEntity = new ResponseEntity(LOGIN_EXPIRE);
        response.setContentType("application/json;charset=utf-8");//指定返回的格式为JSON格式
        response.setCharacterEncoding("UTF-8");//setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
        PrintWriter out = null;
        out = response.getWriter();
        out.write(JSON.toJSONString(responseEntity));
        out.close();
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
