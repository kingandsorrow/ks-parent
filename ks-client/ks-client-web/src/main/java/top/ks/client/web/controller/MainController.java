package top.ks.client.web.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.commodity.api.bean.CommodityBean;
import top.ks.common.util.ResponseEntity;
import top.ks.framework.util.ToolUtil;
import top.ks.rocketmq.producer.TransactionProducer;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>类名称:</b>MainController$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/1/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/1/20
 */
@RestController
public class MainController {
    @Resource
    private TransactionProducer transactionProducer;

    @RequestMapping("main")
    public String main() {
        return "success";
    }

    @RequestMapping("orderList")
    public List<String> orderList() {
        List<String> list = new ArrayList<String>();
        return list;
    }

    @RequestMapping("addOrder")
    public ResponseEntity addOrder() throws MQClientException, UnsupportedEncodingException {
        String topic = "ks-order-topic";
        String tags = null;
        String keys = "201942153203";
        CommodityBean commodityBean = new CommodityBean();
        commodityBean.setCommodityDetail("详情");
        commodityBean.setCommodityId(ToolUtil.randomNums(25));
        commodityBean.setCommodityImg("https://www.souhu.com");
        commodityBean.setCommodityName("商品A");
        commodityBean.setCommodityPrice(0.03);
        commodityBean.setCommodityStock(100);
        commodityBean.setCommodityTitle("A级");
        String body = JSON.toJSONString(commodityBean);
        TransactionSendResult transactionSendResult = transactionProducer.sendTransMessage(topic, tags, keys, body);
        return new ResponseEntity(transactionSendResult.getMsgId());
    }
}
