package top.ks.oss.api.resp;

import top.ks.framework.base.entity.ResponseEntity;
import top.ks.oss.api.bean.OperatorDeatilBean;
import top.ks.oss.api.bean.OperatorDeatilBean;

import java.util.List;

/**
 * <b>类名称:</b>OperatorListResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/6<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/10/6
 */
public class OperatorListResp extends ResponseEntity {

    private List<OperatorDeatilBean> operatorDeatilBeanList;
    private int count;
    public OperatorListResp() {
    }

    public OperatorListResp(String errCode) {
        super(errCode);
    }

    public OperatorListResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public List<OperatorDeatilBean> getOperatorDeatilBeanList() {
        return operatorDeatilBeanList;
    }

    public void setOperatorDeatilBeanList(List<OperatorDeatilBean> operatorDeatilBeanList) {
        this.operatorDeatilBeanList = operatorDeatilBeanList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
