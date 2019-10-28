package top.ks.sso.consumer.resp;


import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.sso.consumer.bean.KsUserBean;

/**
 * <b>类名称:</b>LoginResp$<br/>
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
public class LoginResp extends ResponseEntity {

    private KsUserBean ksUserBean;

    private String token;

    public LoginResp() {
    }

    public LoginResp(String errCode) {
        super(errCode);
    }

    public LoginResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public LoginResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public KsUserBean getKsUserBean() {
        return ksUserBean;
    }

    public void setKsUserBean(KsUserBean ksUserBean) {
        this.ksUserBean = ksUserBean;
    }
}