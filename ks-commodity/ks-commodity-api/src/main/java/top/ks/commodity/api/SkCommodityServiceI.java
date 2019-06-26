package top.ks.commodity.api;

import top.ks.commodity.api.bean.CommodityBean;
import top.ks.commodity.api.req.CommodityRecordReq;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.commodity.api.req.SkCommodityDetailReq;
import top.ks.commodity.api.resp.CommodityRecordResp;
import top.ks.commodity.api.resp.DeducteCommodityResp;
import top.ks.commodity.api.resp.SkCommodityDetailResp;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>SkCommodityServiceI$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/20
 */
public interface SkCommodityServiceI {
    //查询商品详情
    SkCommodityDetailResp skCommodityDetail(SkCommodityDetailReq skCommodityDetailReq);


    ResponseEntity addCommodity(CommodityBean commodityBean);

    DeducteCommodityResp deducteCommodity(DeducteCommodityReq deducteCommodityReq);

    CommodityBean selectOne(String commodityId);

    //查询是否已
    CommodityRecordResp selectCommodityRecord(DeducteCommodityReq deducteCommodityReq);

    ResponseEntity reduce(DeducteCommodityReq deducteCommodityReq);


    ResponseEntity testAop();


}
