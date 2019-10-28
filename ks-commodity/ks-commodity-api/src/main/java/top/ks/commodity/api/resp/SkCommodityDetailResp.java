package top.ks.commodity.api.resp;

import top.ks.commodity.api.bean.SkCommodityDetail;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>SkCommodityDetailResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/3/20
 */
public class SkCommodityDetailResp extends ResponseEntity {

    private SkCommodityDetail skCommodityDetail;

    public SkCommodityDetail getSkCommodityDetail() {
        return skCommodityDetail;
    }

    public void setSkCommodityDetail(SkCommodityDetail skCommodityDetail) {
        this.skCommodityDetail = skCommodityDetail;
    }

    public SkCommodityDetailResp() {
    }

    public SkCommodityDetailResp(String errCode) {
        super(errCode);
    }

    public SkCommodityDetailResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
