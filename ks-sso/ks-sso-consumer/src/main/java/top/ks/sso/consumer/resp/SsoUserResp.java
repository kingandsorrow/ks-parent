package top.ks.sso.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.user.SsoUser;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>SsoUserResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/18<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/18
 */
public class SsoUserResp extends ResponseEntity {

    private SsoUser ssoUser;

    public SsoUserResp() {
    }

    public SsoUserResp(String errCode) {
        super(errCode);
    }

    public SsoUserResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public SsoUser getSsoUser() {
        return ssoUser;
    }

    public void setSsoUser(SsoUser ssoUser) {
        this.ssoUser = ssoUser;
    }

    public SsoUserResp(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
