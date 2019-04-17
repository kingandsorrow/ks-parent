package top.ks.order.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.ks.common.constant.StatusCodeConst;
import top.ks.common.redis.CommodityKey;
import top.ks.common.util.JedisUtil;
import top.ks.framework.util.LogFormat;
import top.ks.order.api.ShoppingServiceI;
import top.ks.order.api.bean.ShopDetailBean;
import top.ks.order.api.req.ShopDetailReq;
import top.ks.order.api.resp.ShopDetailResp;

import static top.ks.common.enums.ResultStatus.SUCCESS;


/**
 * <b>类名称:</b>ShoppingServiceIProxy$<br/>
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
public class ShoppingServiceIProxy implements ShoppingServiceI {

    private static final Log log = LogFactory.getLog(ShoppingServiceIProxy.class);

    @Override
    public ShopDetailResp shopDetail(ShopDetailReq shopDetailReq) {
        try {
            ShopDetailResp shopDetailResp = new ShopDetailResp(SUCCESS.getCode());
            //1.从缓存中获取
            ShopDetailBean shopDetailBean = (ShopDetailBean) JedisUtil.getObjectVal(CommodityKey.commodityDetail, shopDetailReq.getShopId());
            if (shopDetailBean != null) {
                log.info(LogFormat.formatMsg("ShoppingServiceIProxy.shopDetail", "", ""));
                shopDetailResp.setShopDetailBean(shopDetailBean);
                return shopDetailResp;
            }
            //2.没有则从数据库中获取
            ShopDetailBean detailBean = new ShopDetailBean();
            JedisUtil.setObjectVal(CommodityKey.commodityDetail, shopDetailReq.getShopId(), detailBean);
            shopDetailResp.setShopDetailBean(detailBean);
            return shopDetailResp;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("ShoppingServiceIProxy.shopDetail", "system error::" + e.getMessage(), ""));
            return new ShopDetailResp(StatusCodeConst.SYSTEM_ERROR);
        }
    }
}
