package top.ks.sso.consumer.req;


import org.springframework.beans.factory.annotation.Value;
import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>LoginReq$<br/>
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

public class LoginReq extends RequestEntity {
    // 登录方式 0 账号密码登录
    private Integer loginWay;
    // 登录账号
    private String loginName;
    // 登录密码
    private String loginPassWord;
    // 验证码
    private String code;
    // token
    private String token;
    //登录来源
    private int loginFrom;

    private String ossAPiKey;

    private String captcha;

    private boolean redisCache;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassWord() {
        return loginPassWord;
    }

    public void setLoginPassWord(String loginPassWord) {
        this.loginPassWord = loginPassWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(int loginFrom) {
        this.loginFrom = loginFrom;
    }

    public Integer getLoginWay() {
        return loginWay;
    }

    public void setLoginWay(Integer loginWay) {
        this.loginWay = loginWay;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOssAPiKey() {
        return ossAPiKey;
    }

    public void setOssAPiKey(String ossAPiKey) {
        this.ossAPiKey = ossAPiKey;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRedisCache() {
        return redisCache;
    }

    public void setRedisCache(boolean redisCache) {
        this.redisCache = redisCache;
    }
}
