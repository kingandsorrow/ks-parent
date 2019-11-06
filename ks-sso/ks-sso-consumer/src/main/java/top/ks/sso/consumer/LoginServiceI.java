package top.ks.sso.consumer;

import top.ks.sso.consumer.req.LoginOutReq;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.req.RegisterReq;
import top.ks.sso.consumer.req.SsoUserReq;
import top.ks.sso.consumer.resp.LoginOutResp;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.consumer.resp.RegisterResp;
import top.ks.sso.consumer.resp.SsoUserResp;

/**
 * <b>类名称:</b>LoginServiceI$<br/>
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
public interface LoginServiceI {
    //登录统一接口
    LoginResp doLogin(LoginReq loginReq) throws Exception;

    SsoUserResp getUserByToken(SsoUserReq ssoUserReq);

    RegisterResp register(RegisterReq registerReq);

    LoginOutResp loginOut(LoginOutReq loginOutReq);
}
