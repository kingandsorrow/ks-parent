package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.bean.OperatorDeatilBean;

import java.util.Set;

/**
 * <b>类名称:</b>CheckTokenResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/6<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/10/6
 */
public class CheckTokenResp extends ResponseEntity {

    private OperatorDeatilBean operatorDeatilBean;

    private Set<String> permissions;

    public CheckTokenResp() {
    }

    public CheckTokenResp(String errCode) {
        super(errCode);
    }

    public CheckTokenResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public CheckTokenResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public OperatorDeatilBean getOperatorDeatilBean() {
        return operatorDeatilBean;
    }

    public void setOperatorDeatilBean(OperatorDeatilBean operatorDeatilBean) {
        this.operatorDeatilBean = operatorDeatilBean;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
