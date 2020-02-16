package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.bean.KsFunctionBean;
import top.ks.oss.consumer.bean.OperatorDeatilBean;

import java.util.List;

/**
 * <b>类名称:</b>MenuListResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/30<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/11/30
 */
public class MenuListResp extends ResponseEntity {

    private OperatorDeatilBean operatorDeatilBean;

    public MenuListResp() {
    }

    public MenuListResp(String errCode) {
        super(errCode);
    }

    public MenuListResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public MenuListResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public OperatorDeatilBean getOperatorDeatilBean() {
        return operatorDeatilBean;
    }

    public void setOperatorDeatilBean(OperatorDeatilBean operatorDeatilBean) {
        this.operatorDeatilBean = operatorDeatilBean;
    }
}
