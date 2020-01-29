package top.ks.sso.provider.database.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.Login;
import org.springframework.stereotype.Service;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.redis.RedisService;
import top.ks.redis.SsoKey;
import top.ks.sso.consumer.bean.KsOssUserBean;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.provider.database.mapper.KsOssUserMapper;
import top.ks.sso.provider.database.model.KsOssUser;
import top.ks.sso.provider.database.service.LoginService;

import javax.annotation.Resource;

/**
 * <b>类名称:</b>AccountLoginServiceImpl<br/>
 * <b>类注释:</b>账号密码登录<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2019-11-09
 */
@Service
public class AccountLoginServiceImpl implements LoginService {

    private static final Log log = LogFactory.getLog(AccountLoginServiceImpl.class);
    @Resource
    private KsOssUserMapper ksOssUserMapper;
    @Resource
    private RedisService redisService;

    @Override
    public LoginResp doLogin(LoginReq loginReq) {
        if (Strings.hasEmptyStr(loginReq.getLoginName(), loginReq.getLoginPassWord())) {
            log.info(LogFormat.formatMsg("AccountLoginServiceImpl.doLogin", "login no password is null...", ""));
            return new LoginResp(ResultStatus.PARAM_ERROR);
        }
        //1.查询密码账号登录
        String password = SecureUtil.md5(loginReq.getLoginPassWord());
        String loginNo = loginReq.getLoginName();
        String projectId = loginReq.getProjectId();
        KsOssUser ksOssUser = ksOssUserMapper.selectByLogin(password, loginNo, projectId);
        if (ksOssUser == null) {
            log.info(LogFormat.formatMsg("AccountLoginServiceImpl.doLogin", "ksossUser is null..", ""));
            return new LoginResp(ResultStatus.LOGIN_FAIL);
        }
        //2.生成
        KsOssUserBean ksOssUserBean = new KsOssUserBean();
        BeanUtil.copyProperties(ksOssUser, ksOssUserBean);
        String token = UUID.randomUUID().toString();
        //3.往redis放数据
        boolean flag = redisService.set(SsoKey.ssoUserToken, token, ksOssUserBean);
        if (!flag) {
            log.info(LogFormat.formatMsg("AccountLoginServiceImpl.doLogin", "set redis error..", ""));
            return new LoginResp(ResultStatus.LOGIN_FAIL);
        }
        LoginResp loginResp = new LoginResp(ResultStatus.SUCCESS);
        loginResp.setToken(token);
        loginResp.setKsUserBean(ksOssUserBean);
        return loginResp;
    }
}
