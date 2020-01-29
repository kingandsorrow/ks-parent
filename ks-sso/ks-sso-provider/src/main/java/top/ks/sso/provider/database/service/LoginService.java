package top.ks.sso.provider.database.service;

import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.resp.LoginResp;

public interface LoginService {


    LoginResp doLogin(LoginReq loginReq);
}
