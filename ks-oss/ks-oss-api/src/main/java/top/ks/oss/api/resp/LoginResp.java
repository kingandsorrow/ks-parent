package top.ks.oss.api.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.api.bean.OperatorDeatilBean;
import top.ks.oss.api.bean.OperatorDeatilBean;

/**
 * <b>类名称:</b>LoginResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/29<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright 西安创意 2018/9/29
 */
public class LoginResp extends ResponseEntity {
    private OperatorDeatilBean operatorDeatilBean;
    private String token;

    public LoginResp() {
    }

    public LoginResp(String errCode) {
        super(errCode);
    }

    public LoginResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public OperatorDeatilBean getOperatorDeatilBean() {
        return operatorDeatilBean;
    }

    public void setOperatorDeatilBean(OperatorDeatilBean operatorDeatilBean) {
        this.operatorDeatilBean = operatorDeatilBean;
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
}
