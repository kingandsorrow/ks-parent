package top.ks.rocketmq.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;
import top.ks.commodity.api.SkCommodityServiceI;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.commodity.api.resp.CommodityRecordResp;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.redis.CommodityKey;
import top.ks.redis.RedisService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * <b>类名称:</b>OrderListenerImpl$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/1<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/1
 */
@Component
public class OrderListenerImpl implements TransactionListener {
    @Reference(version = "${dubbo.service.version}", retries = -1)
    private SkCommodityServiceI commodityServiceI;
    private final static Log log = LogFactory.getLog(OrderListenerImpl.class);
    @Resource
    private RedisService redisService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            LocalTransactionState state = null;
            String body = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
            DeducteCommodityReq deducteCommodityReq = JSON.parseObject(body, DeducteCommodityReq.class);
            log.info(LogFormat.formatMsg("OrderListenerImpl.executeLocalTransaction", "log id is.." + deducteCommodityReq.getSkOrderId(), ""));
            ResponseEntity responseEntity = commodityServiceI.deducteCommodity(deducteCommodityReq);
            if (!"0".equals(responseEntity.getErrCode())) {
                Long count = redisService.incr(CommodityKey.commodityStock, "" + deducteCommodityReq.getCommodityId());
                log.info(LogFormat.formatMsg("OrderListenerImpl.executeLocalTransaction", "deducteCommodity fail... && count is.." + count, ""));
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.error(LogFormat.formatMsg("OrderListenerImpl.executeLocalTransaction", "system error is::" + e.getMessage(), ""));
            return LocalTransactionState.UNKNOW;
        }

    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        DeducteCommodityReq deducteCommodityReq = null;
        try {
            String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            deducteCommodityReq = JSON.parseObject(body, DeducteCommodityReq.class);
            log.info(LogFormat.formatMsg("OrderListenerImpl.checkLocalTransaction", "checkLocalTransaction is.." + deducteCommodityReq.getSkOrderId(), ""));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CommodityRecordResp commodityRecordResp = commodityServiceI.selectCommodityRecord(deducteCommodityReq);
        if (!"0".equals(commodityRecordResp.getErrCode())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }


}
