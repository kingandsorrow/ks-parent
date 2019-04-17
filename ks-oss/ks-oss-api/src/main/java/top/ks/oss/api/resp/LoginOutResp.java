package top.ks.oss.api.resp;

import top.ks.framework.base.entity.ResponseEntity;

/**
 * <b>类名称:</b>LoginOutResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/13<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/10/13
 */
public class LoginOutResp extends ResponseEntity {

    public LoginOutResp() {
    }

    public LoginOutResp(String errCode) {
        super(errCode);
    }

    public LoginOutResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
