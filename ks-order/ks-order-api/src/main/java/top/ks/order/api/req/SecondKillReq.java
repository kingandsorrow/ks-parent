package top.ks.order.api.req;

import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>SecondKillReq$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/2/28<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/2/28
 */
public class SecondKillReq extends RequestEntity {
    private String userId;
    //商品id
    private String commodityId;

    private String skOrderId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getSkOrderId() {
        return skOrderId;
    }

    public void setSkOrderId(String skOrderId) {
        this.skOrderId = skOrderId;
    }
}
