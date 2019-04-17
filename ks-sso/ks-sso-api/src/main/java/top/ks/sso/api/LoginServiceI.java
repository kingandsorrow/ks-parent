package top.ks.sso.api;

import top.ks.sso.api.req.LoginOutReq;
import top.ks.sso.api.req.LoginReq;
import top.ks.sso.api.req.SsoUserReq;
import top.ks.sso.api.resp.LoginOutResp;
import top.ks.sso.api.resp.LoginResp;
import top.ks.sso.api.resp.SsoUserResp;

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
 * Copyright 西安创意 2018/12/12
 */
public interface LoginServiceI {

    LoginResp doLogin(LoginReq loginReq);

    SsoUserResp getUserByToken(SsoUserReq ssoUserReq);

    LoginOutResp loginOut(LoginOutReq loginOutReq);
}
