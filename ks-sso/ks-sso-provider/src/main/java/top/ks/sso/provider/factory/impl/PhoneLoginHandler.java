package top.ks.sso.provider.factory.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.common.constant.Const;
import top.ks.common.user.SsoUser;
import top.ks.common.util.ErrorCode;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.redis.RedisService;
import top.ks.redis.SsoKey;
import top.ks.sso.consumer.bean.KsUserBean;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.provider.database.mapper.KsUserAuthsMapper;
import top.ks.sso.provider.database.mapper.KsUserMapper;
import top.ks.sso.provider.database.model.KsUser;
import top.ks.sso.provider.database.service.KsUserService;
import top.ks.sso.provider.factory.LoginHandler;

import javax.annotation.Resource;
import java.util.Date;

import static top.ks.common.enums.ResultStatus.*;

/**
 * <b>类名称:</b>PhoneLoginHandler$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b>手机号登录实现<br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/6/20<br/>
 * <b>修改备注:</b><br/>
 *
 * <p>
 * Copyright KS 2018/6/20
 */
@Component
public class PhoneLoginHandler extends LoginHandler {

    private static final Log log = LogFactory.getLog(PhoneLoginHandler.class);

    @Resource
    private KsUserAuthsMapper ksUserAuthsMapper;
    @Resource
    private KsUserService ksUserService;
    @Resource
    private KsUserMapper ksUserMapper;
    @Resource
    private RedisService redisService;

    @Override
    public LoginResp loginMethod(LoginReq loginReq) {
        try {
            log.info(LogFormat.formatMsg("PhoneLoginHandler.loginMethod", "userLoginReq is::" + loginReq.toJsonStr(), ""));
            // 1.校验验证码
            ErrorCode errorCode = checkPhoneCode(loginReq);
            if (errorCode != null) {
                log.info(LogFormat.formatMsg("PhoneLoginHandler.login", "check phone code is wrong..", ""));
                return new LoginResp(errorCode.getCode(), errorCode.getMsg());
            }
            // 2.判断用户是否存在
            KsUser ksUser = ksUserMapper.selectByPhoneWay(loginReq.getLoginName());
            // 3.走注册流程
            if (ksUser == null) {
                log.info(LogFormat.formatMsg("PhoneLoginHandler.loginMethod", "", ""));
                return this.phoneRegister(loginReq);
            }
            //4.走登录流程
            return this.phoneLogin(loginReq, ksUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(LogFormat.formatMsg("PhoneLoginHandler.loginMethod", "catch system error is::" + e.getMessage(), ""));
            return new LoginResp(SYSTEM_ERROR);
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :走电话登录流程
     * @author : brj
     * @CreateDate : 2018/12/16 23:26
     */
    private LoginResp phoneLogin(LoginReq loginReq, KsUser ksUser) {
        SsoUser ssoUser = new SsoUser();
        ssoUser.setUserId(ksUser.getUserId());
        ssoUser.setUserName(ksUser.getNickName());
        ssoUser.setExpireMinite(SsoKey.ssoUserToken.expireSeconds());
        ssoUser.setExpireFreshTime(System.currentTimeMillis());
        String token = IdUtil.simpleUUID();
        redisService.set(SsoKey.ssoUserToken, token, ssoUser);
        KsUserBean ksUserBean = initKsUserBean(ksUser);
        LoginResp loginResp = new LoginResp(SUCCESS);
        loginResp.setKsUserBean(ksUserBean);
        loginResp.setToken(token);
        return loginResp;
    }

    private KsUserBean initKsUserBean(KsUser ksUser) {
        KsUserBean ksUserBean = new KsUserBean();
        ksUserBean.setUserId(ksUser.getUserId());
        ksUserBean.setNickName(ksUser.getNickName());
        ksUserBean.setCreateTime(DateUtil.format(ksUser.getCreateTime(), "YYYY-MM-dd hh:mm:ss"));
        ksUserBean.setOtherInfo(ksUser.getOtherInfo());
        ksUserBean.setGender(ksUser.getGender());
        ksUserBean.setRegistFrom(ksUser.getRegistFrom());
        ksUserBean.setPhone(ksUser.getPhone());
        return ksUserBean;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :注册&登录
     * @author : brj
     * @CreateDate : 2018/12/16 23:38
     */
    private LoginResp phoneRegister(LoginReq loginReq) {
        KsUser ksUser = new KsUser();
        ksUser.setPhone(loginReq.getLoginName());
        ksUser.setUserId(IdUtil.createSnowflake(1, 1).nextId() + "");
        ksUser.setCreateTime(new Date());
        ksUser.setGender((byte) 0);
        ksUser.setNickName(ksUser.getPhone().substring(0, 3) + "****" + ksUser.getPhone().substring(7));
        ksUser.setRegistFrom((byte) 1);
        ksUser.setFrezzType((byte) 1);
        ksUser.setCreateTime(new Date());
        KsUserBean ksUserBean = initKsUserBean(ksUser);
        int row = ksUserService.registerUser(ksUser);
        //注册完登录
        return this.phoneLogin(loginReq, ksUser);
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :校验验证码
     * @author : brj
     * @CreateDate : 2018/3/26 14:48
     */
    private ErrorCode checkPhoneCode(LoginReq userLoginReq) {
        String phone = userLoginReq.getLoginName();

        String code = userLoginReq.getCode();
        if (Strings.isEmpty(phone)) {
            return new ErrorCode(PHONE_IS_WRONG);
        }
        if (Strings.isEmpty(code)) {
            return new ErrorCode(CODE_IS_WRONG);
        }
        //boolean codeFlag = redisHelper.checkSmsCode(phone, code);
        boolean codeFlag = Const.CODE_DEFAULT.equals(userLoginReq.getCode());
        if (!codeFlag) {
            return new ErrorCode(CODE_IS_WRONG);
        }
        return null;
    }


}
