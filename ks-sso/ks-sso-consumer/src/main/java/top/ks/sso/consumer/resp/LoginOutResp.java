package top.ks.sso.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>LoginOutResp$<br/>
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
public class LoginOutResp extends ResponseEntity {

    public LoginOutResp() {
    }

    public LoginOutResp(String errCode) {
        super(errCode);
    }

    public LoginOutResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public LoginOutResp(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
