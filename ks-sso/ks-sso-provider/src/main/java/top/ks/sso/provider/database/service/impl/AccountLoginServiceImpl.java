package top.ks.sso.provider.database.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.ks.common.enums.ResultStatus;
import top.ks.common.user.SsoUser;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.common.util.ToolUtil;
import top.ks.redis.RedisService;
import top.ks.redis.SsoKey;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.provider.database.mapper.KsOssUserMapper;
import top.ks.sso.provider.database.model.KsOssUser;
import top.ks.sso.provider.database.service.LoginService;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

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
        KsOssUser ksOssUser = ksOssUserMapper.selectByLogin(password, loginNo, projectId, null);
        if (ksOssUser == null) {
            log.info(LogFormat.formatMsg("AccountLoginServiceImpl.doLogin", "ksossUser is null..", ""));
            return new LoginResp(ResultStatus.LOGIN_FAIL);
        }
        //2.生成
        SsoUser ssoUser = new SsoUser();
        BeanUtil.copyProperties(ksOssUser, ssoUser);
        ssoUser.setUserId(ksOssUser.getOssUserId());
        ssoUser.setUserName(ksOssUser.getLoginNo());
        String jwt = createJWT(loginReq.getOssAPiKey(), ToolUtil.getStringID(), ksOssUser.getOssUserId(), JSON.toJSONString(ssoUser), SsoKey.ssoUserToken.expireSeconds() * 1000);
        //3.往redis放数据
        if (loginReq.isRedisCache()) {
            boolean flag = redisService.set(SsoKey.ssoUserToken, jwt, ssoUser);
            if (!flag) {
                log.info(LogFormat.formatMsg("AccountLoginServiceImpl.doLogin", "set redis error..", ""));
                return new LoginResp(ResultStatus.LOGIN_FAIL);
            }
        }
        LoginResp loginResp = new LoginResp(ResultStatus.SUCCESS);
        loginResp.setToken(jwt);
        loginResp.setSsoUser(ssoUser);
        return loginResp;
    }

    /**
     * @param :
     * @param ossAPiKey
     * @return :
     * @Method :
     * @Description :创建jwt
     * @author : birjc
     * @CreateDate : 2020-02-02 09:02
     */
    private String createJWT(String ossAPiKey, String id, String issuer, String subject, long ttlMillis) {

//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

//We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(ossAPiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey, signatureAlgorithm);

//if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

//Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


}
