package top.ks.sso.provider.oss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.common.user.SsoUser;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.redis.RedisService;
import top.ks.redis.SsoKey;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.req.LoginOutReq;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.req.RegisterReq;
import top.ks.sso.consumer.req.SsoUserReq;
import top.ks.sso.consumer.resp.LoginOutResp;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.consumer.resp.RegisterResp;
import top.ks.sso.consumer.resp.SsoUserResp;
import top.ks.sso.provider.factory.LoginFactory;
import top.ks.sso.provider.factory.LoginHandler;

import javax.annotation.Resource;

import static top.ks.common.enums.ResultStatus.*;


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
 * Copyright KS 2018/12/12
 */
@Component
public class OssLoginServiceIProxy implements LoginServiceI {

    private static final Log log = LogFactory.getLog(OssLoginServiceIProxy.class);
    @Resource
    private LoginFactory loginFactory;
    @Resource
    private RedisService redisService;

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :登录&注册
     * @author : brj
     * @CreateDate : 2018/12/12 23:17
     */
    @Override
    public LoginResp doLogin(LoginReq loginReq) throws Exception {
        if (Strings.isEmpty(loginReq.getLoginWay())) {
            log.info(LogFormat.formatMsg("LoginServiceIProxy.login", "login way is null.." + loginReq.toJsonStr(), ""));
            return new LoginResp(SUCCESS);
        }
        LoginHandler loginHandler = loginFactory.getLoginHandler(loginReq.getLoginWay());
        if (loginHandler == null) {
            log.info(LogFormat.formatMsg("LoginServiceIProxy.doLogin", "get LoginHandler is null..", ""));
            return new LoginResp();
        }
        LoginResp loginResp = loginHandler.loginMethod(loginReq);
        return loginResp;
    }

    @Override
    public SsoUserResp getUserByToken(SsoUserReq ssoUserReq) {
        if (Strings.isEmpty(ssoUserReq.getToken())) {
            log.info(LogFormat.formatMsg("LoginServiceIProxy.getUserByToken", "token is empty.." + ssoUserReq.toJsonStr(), ""));
            return new SsoUserResp(PARAM_ERROR);
        }
        SsoUser ssoUser = redisService.get(SsoKey.ssoUserToken, ssoUserReq.getToken(), SsoUser.class);
        if (ssoUser != null) {
            if ((System.currentTimeMillis() - ssoUser.getExpireFreshTime()) > ssoUser.getExpireMinite() / 2) {
                ssoUser.setExpireFreshTime(System.currentTimeMillis());
                redisService.set(SsoKey.ssoUserToken, ssoUserReq.getToken(), ssoUser);
            }
            SsoUserResp ssoUserResp = new SsoUserResp(SUCCESS);
            ssoUserResp.setSsoUser(ssoUser);
            return ssoUserResp;
        }
        return new SsoUserResp(DATA_NOT_EXSIT);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :统一注册接口
     * @author : birjc
     * @CreateDate : 2019-09-17 22:26
     */
    @Override
    public RegisterResp register(RegisterReq registerReq) {
        return null;
    }

    @Override
    public LoginOutResp loginOut(LoginOutReq loginOutReq) {
        try {
            LoginOutResp loginOutResp = new LoginOutResp(SUCCESS);
            redisService.del(loginOutReq.getToken());
            return loginOutResp;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("LoginServiceIProxy.loginOut", "system error::" + e.getMessage(), ""));
            return new LoginOutResp(SYSTEM_ERROR);
        }

    }


}
