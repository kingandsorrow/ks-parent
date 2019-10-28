package top.ks.sso.consumer.req;

import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>LoginOutReq$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/2/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/2/20
 */
public class LoginOutReq extends RequestEntity {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
