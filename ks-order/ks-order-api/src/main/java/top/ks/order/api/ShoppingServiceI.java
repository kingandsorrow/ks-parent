package top.ks.order.api;

import top.ks.order.api.req.ShopDetailReq;
import top.ks.order.api.resp.ShopDetailResp;

/**
 * <b>类名称:</b>ShoppingServiceI$<br/>
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
public interface ShoppingServiceI {


    ShopDetailResp shopDetail(ShopDetailReq shopDetailReq);

}
