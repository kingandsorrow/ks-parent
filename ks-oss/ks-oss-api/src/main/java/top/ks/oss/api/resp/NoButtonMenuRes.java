package top.ks.oss.api.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.api.bean.KsFunctionBean;

import java.util.List;

/**
 * <b>类名称:</b>NoButtonMenuRes$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/2<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/2
 */
public class NoButtonMenuRes extends ResponseEntity {

    private List<KsFunctionBean> ksFunctionBeans;

    public NoButtonMenuRes() {
    }

    public NoButtonMenuRes(String errCode) {
        super(errCode);
    }

    public NoButtonMenuRes(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public NoButtonMenuRes(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public List<KsFunctionBean> getKsFunctionBeans() {
        return ksFunctionBeans;
    }

    public void setKsFunctionBeans(List<KsFunctionBean> ksFunctionBeans) {
        this.ksFunctionBeans = ksFunctionBeans;
    }
}
