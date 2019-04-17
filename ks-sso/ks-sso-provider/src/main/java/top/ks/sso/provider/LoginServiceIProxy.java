package top.ks.sso.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.common.constant.StatusCodeConst;
import top.ks.common.store.SsoLoginStore;
import top.ks.common.user.SsoUser;
import top.ks.framework.util.LogFormat;
import top.ks.framework.util.StatusCodeManager;
import top.ks.framework.util.Strings;
import top.ks.sso.api.LoginServiceI;
import top.ks.sso.api.req.LoginOutReq;
import top.ks.sso.api.req.LoginReq;
import top.ks.sso.api.req.SsoUserReq;
import top.ks.sso.api.resp.LoginOutResp;
import top.ks.sso.api.resp.LoginResp;
import top.ks.sso.api.resp.SsoUserResp;
import top.ks.sso.provider.factory.LoginFactory;
import top.ks.sso.provider.factory.LoginHandler;

import javax.annotation.Resource;

/**
 * <b>类名称:</b>LoginServiceIProxy$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/12<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/12
 */
@Service(
        version = "${dubbo.application.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Component
public class LoginServiceIProxy implements LoginServiceI {

    private static final Log log = LogFactory.getLog(LoginServiceIProxy.class);
    @Resource
    private LoginFactory loginFactory;


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :登录&注册
     * @author : brj
     * @CreateDate : 2018/12/12 23:17
     */
    @Override
    public LoginResp doLogin(LoginReq loginReq) {
        if (Strings.isEmpty(loginReq.getLoginWay())) {
            log.info(LogFormat.formatMsg("LoginServiceIProxy.login", "login way is null.." + loginReq.toJsonStr(), ""));
            return new LoginResp(StatusCodeConst.PARAMS_NULL);
        }
        LoginHandler loginHandler = loginFactory.getLoginHandler(loginReq.getLoginWay());
        if (loginHandler == null) {
            log.info(LogFormat.formatMsg("LoginServiceIProxy.doLogin", "get LoginHandler is null..", ""));
            return new LoginResp(StatusCodeConst.SYSTEM_ERROR);
        }
        LoginResp loginResp = loginHandler.loginMethod(loginReq);
        return loginResp;
    }

    @Override
    public SsoUserResp getUserByToken(SsoUserReq ssoUserReq) {
        if (Strings.isEmpty(ssoUserReq.getToken())) {
            log.info(LogFormat.formatMsg("LoginServiceIProxy.getUserByToken", "token is empty.." + ssoUserReq.toJsonStr(), ""));
            return new SsoUserResp(StatusCodeConst.PARAMS_NULL);
        }
        SsoUser ssoUser = SsoLoginStore.get(ssoUserReq.getToken());
        if (ssoUser != null) {
            if ((System.currentTimeMillis() - ssoUser.getExpireFreshTime()) > ssoUser.getExpireMinite() / 2) {
                ssoUser.setExpireFreshTime(System.currentTimeMillis());
                SsoLoginStore.put(ssoUserReq.getToken(), ssoUser);
            }
            SsoUserResp ssoUserResp = new SsoUserResp(StatusCodeConst.SUCCESS);
            ssoUserResp.setSsoUser(ssoUser);
            return ssoUserResp;
        }
        return new SsoUserResp(StatusCodeConst.DATA_NOT_EXSIT);
    }

    @Override
    public LoginOutResp loginOut(LoginOutReq loginOutReq) {
        try {
            LoginOutResp loginOutResp = new LoginOutResp(StatusCodeConst.SUCCESS);
            SsoLoginStore.remove(loginOutReq.getToken());
            return loginOutResp;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("LoginServiceIProxy.loginOut", "system error::" + e.getMessage(), ""));
            return new LoginOutResp(StatusCodeConst.SYSTEM_ERROR);
        }

    }


}
