package top.ks.commodity.api.resp;

import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>DeducteCommodityResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/9<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/9
 */
public class DeducteCommodityResp extends ResponseEntity {

    public DeducteCommodityResp() {
    }

    public DeducteCommodityResp(String errCode) {
        super(errCode);
    }

    public DeducteCommodityResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
