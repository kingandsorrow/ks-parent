package top.ks.order.api.resp;

import top.ks.framework.base.entity.ResponseEntity;
import top.ks.order.api.bean.ShopDetailBean;

/**
 * <b>类名称:</b>ShopDetailResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/3<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/3
 */
public class ShopDetailResp extends ResponseEntity {

    private ShopDetailBean shopDetailBean;

    public ShopDetailResp() {
    }

    public ShopDetailResp(String errCode) {
        super(errCode);
    }

    public ShopDetailResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public ShopDetailBean getShopDetailBean() {
        return shopDetailBean;
    }

    public void setShopDetailBean(ShopDetailBean shopDetailBean) {
        this.shopDetailBean = shopDetailBean;
    }
}
