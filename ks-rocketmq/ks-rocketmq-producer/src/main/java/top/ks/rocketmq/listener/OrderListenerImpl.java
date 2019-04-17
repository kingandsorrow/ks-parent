package top.ks.rocketmq.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;
import top.ks.commodity.api.SkCommodityServiceI;
import top.ks.commodity.api.bean.CommodityBean;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.commodity.api.resp.CommodityRecordResp;
import top.ks.framework.base.entity.ResponseEntity;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

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
    @Reference(version = "${dubbo.service.version}")
    private SkCommodityServiceI commodityServiceI;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            LocalTransactionState state = null;
            String body = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
            DeducteCommodityReq deducteCommodityReq = JSON.parseObject(body, DeducteCommodityReq.class);
            ResponseEntity responseEntity = commodityServiceI.deducteCommodity(deducteCommodityReq);
            if (!"0".equals(responseEntity.getErrCode())) {
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            e.printStackTrace();
            return LocalTransactionState.UNKNOW;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        DeducteCommodityReq deducteCommodityReq = null;
        try {
            String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            deducteCommodityReq = JSON.parseObject(body, DeducteCommodityReq.class);
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
