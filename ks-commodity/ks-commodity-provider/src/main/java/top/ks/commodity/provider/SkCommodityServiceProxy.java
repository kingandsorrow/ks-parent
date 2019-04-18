package top.ks.commodity.provider;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.commodity.api.SkCommodityServiceI;
import top.ks.commodity.api.bean.CommodityBean;
import top.ks.commodity.api.bean.SkCommodityDetail;
import top.ks.commodity.api.req.CommodityRecordReq;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.commodity.api.req.SkCommodityDetailReq;
import top.ks.commodity.api.resp.CommodityRecordResp;
import top.ks.commodity.api.resp.DeducteCommodityResp;
import top.ks.commodity.api.resp.SkCommodityDetailResp;
import top.ks.commodity.database.mapper.CommodityMapper;
import top.ks.commodity.database.mapper.SkCommodityMapper;
import top.ks.commodity.database.mapper.SkRecordMapper;
import top.ks.commodity.database.model.Commodity;
import top.ks.commodity.database.model.SkCommodity;
import top.ks.commodity.database.model.SkRecord;
import top.ks.commodity.database.service.SkCommodityService;
import top.ks.common.redis.CommodityKey;
import top.ks.common.redis.RedisService;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.common.util.ToolUtil;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static top.ks.common.enums.ResultStatus.*;

/**
 * <b>类名称:</b>SkCommodityServiceProxy$<br/>
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
@Service(
        version = "${dubbo.application.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Component
public class SkCommodityServiceProxy implements SkCommodityServiceI {
    @Resource
    private SkCommodityMapper skCommodityMapper;

    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private SkCommodityService commodityService;
    @Resource
    private SkRecordMapper skRecordMapper;
    @Resource
    private RedisService redisService;

    private AtomicInteger checkTimes = new AtomicInteger();

    private static final Log log = LogFactory.getLog(SkCommodityServiceProxy.class);

    @Override
    public SkCommodityDetailResp skCommodityDetail(SkCommodityDetailReq skCommodityDetailReq) {
        try {
            SkCommodityDetailResp resp = new SkCommodityDetailResp(SUCCESS.getCode(), SUCCESS.getMessage());
            SkCommodityDetail skCommodityDetail = redisService.get(CommodityKey.commodityDetail, skCommodityDetailReq.getCommodityId(), SkCommodityDetail.class);
            if (skCommodityDetail != null) {
                log.info(LogFormat.formatMsg("SkCommodityServiceProxy.skCommodityDetail", "", ""));
                resp.setSkCommodityDetail(skCommodityDetail);
                return resp;
            }
            SkCommodity skCommodity = skCommodityMapper.selectByCommodityId(skCommodityDetailReq.getCommodityId());
            if (skCommodity == null) {
                log.info(LogFormat.formatMsg("SkCommodityServiceProxy.skCommodityDetail", "getCommodityId is null..", ""));
                return new SkCommodityDetailResp(MIAOSHA_NOT_EXIST.getCode(), MIAOSHA_NOT_EXIST.getMessage());
            }
            SkCommodityDetail skDetail = convertDetail(skCommodity);
            redisService.set(CommodityKey.commodityDetail, skDetail.getCommodityId(), skCommodity);
            resp.setSkCommodityDetail(skDetail);
            return resp;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("SkCommodityServiceProxy.skCommodityDetail", "system error::" + e.getMessage(), ""));
            return new SkCommodityDetailResp(EXCEPTION.getCode(), EXCEPTION.getMessage());
        }
    }

    @Override
    public ResponseEntity addCommodity(CommodityBean commodityBean) {
        Commodity commodity = new Commodity();
        BeanUtil.copyProperties(commodityBean, commodity);
        commodity.setCommodityPrice(BigDecimal.valueOf(commodityBean.getCommodityPrice()));
        commodity.setCommodityStock(commodityBean.getCommodityStock());
        commodityMapper.insertSelective(commodity);
        return new ResponseEntity(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public DeducteCommodityResp deducteCommodity(DeducteCommodityReq deducteCommodityReq) {
        SkCommodity skCommodity = skCommodityMapper.selectByCommodityId(deducteCommodityReq.getCommodityId());
        if (skCommodity.getSkStockCount() <= 0) {
            log.info(LogFormat.formatMsg("SkCommodityServiceProxy.deducteCommodity", "skCommodity stock is not enough.." + JSON.toJSONString(skCommodity), ""));
            return new DeducteCommodityResp(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
        }
        int row = commodityService.deducteCommodity(deducteCommodityReq.getUserId(), deducteCommodityReq.getCommodityId(), deducteCommodityReq.getSkOrderId());
        if (row <= 0) {
            log.info(LogFormat.formatMsg("SkCommodityServiceProxy.deducteCommodity", "skCommodity deducteCommodity fail.." + JSON.toJSONString(skCommodity), ""));
            return new DeducteCommodityResp(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
        }
        return new DeducteCommodityResp(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2019/4/2 22:18
     */
    @Override
    public CommodityBean selectOne(String commodityId) {
        Commodity commodity = commodityMapper.selectByPrimaryKey(commodityId);
        if (commodity != null) {
            CommodityBean commodityBean = new CommodityBean();
            return commodityBean;
        }
        return null;
    }

    @Override
    public CommodityRecordResp selectCommodityRecord(DeducteCommodityReq deducteCommodityReq) {
        CommodityRecordResp commodityRecordResp = new CommodityRecordResp();
        SkRecord skRecord = skRecordMapper.selectByOrderId(deducteCommodityReq.getSkOrderId());
        if (skRecord == null) {
            return new CommodityRecordResp(ORDER_NOT_EXIST.getCode(), ORDER_NOT_EXIST.getMessage());
        }
        return new CommodityRecordResp(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    private SkCommodityDetail convertDetail(SkCommodity skCommodity) {
        SkCommodityDetail skCommodityDetail = new SkCommodityDetail();
        BeanUtil.copyProperties(skCommodity, skCommodityDetail);
        skCommodityDetail.setCommodityId(skCommodity.getCommodityId() + "");
        skCommodityDetail.setSkPrice(skCommodity.getSkPrice().doubleValue());
        skCommodityDetail.setStartDate(ToolUtil.getDateStr(skCommodity.getStartDate()));
        skCommodityDetail.setEndDate(ToolUtil.getDateStr(skCommodity.getEndDate()));
        return skCommodityDetail;
    }
}
