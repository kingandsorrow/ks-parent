package top.ks.sso.provider.factory;

import top.ks.sso.api.req.LoginReq;
import top.ks.sso.api.resp.LoginResp;

/**
 * <b>类名称:</b>LoginHandler$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/6/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 * <p>
 * Copyright 西安创意 2018/6/20
 */
public abstract class LoginHandler {

    public abstract LoginResp loginMethod(LoginReq loginReq);

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :创建userBean
     * @author : brj
     * @CreateDate : 2018/6/19 16:24
     */
    /*protected UserBean generateBean(SkyTreasureUser treasureUser, SkyTreasureUserAuths userAuths) {
        UserBean userBean = new UserBean();
        if (treasureUser != null) {
            Beans.fromObj(treasureUser, userBean).copy();
        } else {
            Beans.fromObj(userAuths, userBean).copy();
        }
        if (userAuths != null && userAuths.getIdentityType() == 1) {
            userBean.setOpenId(userAuths.getIdentifier());
        }
        return userBean;
    }*/
}
