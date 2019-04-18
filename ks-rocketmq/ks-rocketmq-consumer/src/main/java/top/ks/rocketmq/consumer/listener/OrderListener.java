package top.ks.rocketmq.consumer.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import top.ks.commodity.api.bean.CommodityBean;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ToolUtil;
import top.ks.order.api.SecondKillServiceI;
import top.ks.order.api.req.SecondKillReq;
import top.ks.order.api.resp.SecondKillResp;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <b>类名称:</b>MessageListener$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/29<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/29
 */
@Component
public class OrderListener implements MessageListenerConcurrently {

    private static final Log log = LogFactory.getLog(OrderListener.class);
    @Reference(version = "${dubbo.service.version}")
    private SecondKillServiceI secondKillServiceI;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (list != null) {
            for (MessageExt ext : list) {
                try {
                    String body = new String(ext.getBody(), "UTF-8");
                    DeducteCommodityReq deducteCommodityReq = JSON.parseObject(body, DeducteCommodityReq.class);
                    SecondKillReq secondKillReq = new SecondKillReq();
                    secondKillReq.setCommodityId(deducteCommodityReq.getCommodityId());
                    secondKillReq.setSkOrderId(deducteCommodityReq.getSkOrderId());
                    secondKillReq.setUserId(deducteCommodityReq.getUserId());
                    SecondKillResp secondKillResp = secondKillServiceI.secondKillOrder(secondKillReq);
                    log.info(LogFormat.formatMsg("MessageListener.consumeMessage", "secondKillResp is.." + secondKillResp.toJsonStr(), ""));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;


    }
}
