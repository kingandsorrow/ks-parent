package top.ks.sso.core.resp;

import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>SsoResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/2/11<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/2/11
 */
public class SsoResp extends ResponseEntity {

    private String loginUrl;

    public SsoResp() {
    }

    public SsoResp(String errCode) {
        super(errCode);
    }

    public SsoResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
