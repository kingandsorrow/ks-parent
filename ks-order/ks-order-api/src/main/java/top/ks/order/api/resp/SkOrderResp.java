package top.ks.order.api.resp;

import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>SkOrderResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/8<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/8
 */
public class SkOrderResp extends ResponseEntity {

    public SkOrderResp() {
    }

    public SkOrderResp(String errCode) {
        super(errCode);
    }

    public SkOrderResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
