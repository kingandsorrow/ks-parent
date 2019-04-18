package top.ks.order.provider;

import cn.hutool.core.util.IdUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.order.api.SecondKillServiceI;
import top.ks.order.api.req.SecondKillReq;
import top.ks.order.api.req.SkOrderReq;
import top.ks.order.api.resp.SecondKillResp;
import top.ks.order.api.resp.SkOrderResp;
import top.ks.order.database.mapper.SkOrderMapper;
import top.ks.order.database.model.SkOrder;

import javax.annotation.Resource;

import static top.ks.common.enums.ResultStatus.*;

/**
 * <b>类名称:</b>SecondKillProxy$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/2/28<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/2/28
 */
@Service(
        version = "${dubbo.application.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class SecondKillServiceIProxy implements SecondKillServiceI {

    private static final Log log = LogFactory.getLog(SecondKillServiceIProxy.class);
    @Resource
    private SkOrderMapper skOrderMapper;

    @Override
    public SecondKillResp secondKillOrder(SecondKillReq secondKillReq) {
        SkOrder skOrder = skOrderMapper.selectByUserCommodity(secondKillReq.getCommodityId(), secondKillReq.getUserId());
        if (skOrder != null) {
            log.info(LogFormat.formatMsg("SecondKillServiceIProxy.skOrder", "check sk order is empty..", ""));
            return new SecondKillResp(REPEATE_MIAOSHA.getCode(), REPEATE_MIAOSHA.getMessage());
        }
        SkOrder order = new SkOrder();
        order.setOrderId(IdUtil.objectId());
        order.setUserId(secondKillReq.getUserId());
        order.setSkOrderId(secondKillReq.getSkOrderId());
        order.setCommodityId(secondKillReq.getCommodityId());
        int row = skOrderMapper.insertSelective(order);
        if (row <= 0) {
            log.info(LogFormat.formatMsg("SecondKillServiceIProxy.secondKillOrder", "insert order fail..", ""));
            return new SecondKillResp(MIAOSHA_FAIL.getCode(), MIAOSHA_FAIL.getMessage());
        }
        return new SecondKillResp(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public SkOrderResp skOrder(SkOrderReq skOrderReq) {
        SkOrder skOrder = skOrderMapper.selectByUserCommodity(skOrderReq.getCommodityId(), skOrderReq.getUserId());
        if (skOrder == null) {
            log.info(LogFormat.formatMsg("SecondKillServiceIProxy.skOrder", "check sk order is empty..", ""));
            return new SkOrderResp(ORDER_NOT_EXIST.getCode(), ORDER_NOT_EXIST.getMessage());
        }
        SkOrderResp skOrderResp = new SkOrderResp(SUCCESS.getCode(), SUCCESS.getMessage());
        return skOrderResp;
    }
}
