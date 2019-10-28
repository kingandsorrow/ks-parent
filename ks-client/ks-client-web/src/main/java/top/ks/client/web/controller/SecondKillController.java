package top.ks.client.web.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.client.web.helper.vo.SkResponseEntity;
import top.ks.commodity.api.SkCommodityServiceI;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.common.constant.Const;
import top.ks.common.util.*;
import top.ks.order.api.SecondKillServiceI;
import top.ks.order.api.req.SecondKillReq;
import top.ks.order.api.req.SkOrderReq;
import top.ks.order.api.resp.SkOrderResp;
import top.ks.redis.CommodityKey;
import top.ks.redis.CommonKey;
import top.ks.redis.RedisService;
import top.ks.rocketmq.producer.TransactionProducer;

import javax.annotation.Resource;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static top.ks.common.enums.ResultStatus.*;

/**
 * <b>类名称:</b>SecondKillController$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/8<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/8
 */
@RestController
public class SecondKillController {

    private static final Log log = LogFactory.getLog(SecondKillController.class);

    @Resource
    private RedisService redisService;
    @Reference(version = "${dubbo.service.version}")
    private SecondKillServiceI secondKillServiceI;
    @Reference(version = "${dubbo.service.version}")
    private SkCommodityServiceI commodityServiceI;
    @Resource
    private TransactionProducer transactionProducer;

    @RequestMapping("secondKill")
    public SkResponseEntity secondKill(DeducteCommodityReq deducteCommodityReq) throws MQClientException, UnsupportedEncodingException {
        //1.校验用户信息
        if (Strings.isEmpty(deducteCommodityReq.getUserId())) {
            log.info(LogFormat.formatMsg("SecondKillServiceIProxy.secondKillOrder", "", ""));
            return new SkResponseEntity(PARAM_ERROR.getCode(), PARAM_ERROR.getMessage());
        }
        //2.校验该用户是否有频繁访问
        String repeatCheckKey = deducteCommodityReq.getUserId() + Const.REDIS_CONCAT_SIGN + deducteCommodityReq.getCommodityId();
        long commitTimes = redisService.repeatCheck(CommonKey.repeatCheck, repeatCheckKey);
        if (commitTimes > 1) {
            log.info(LogFormat.formatMsg("SecondKillController.secondKill", "repeatCheckKey::" + repeatCheckKey + " commit too many times..", ""));
            return new SkResponseEntity(REQUEST_ILLEGAL.getCode(), REQUEST_ILLEGAL.getMessage());
        }
        //3.限流操作（分布式限流）
        boolean flag = redisService.accquireToken(CommonKey.accessLimit, Const.DIS_LIMIT_KEY);
        if (!flag) {
            log.info(LogFormat.formatMsg("SecondKillController.secondKill", "get limit access..", ""));
            return new SkResponseEntity(JOIN_USER_TOO_MUCH.getCode(), JOIN_USER_TOO_MUCH.getMessage());
        }
        //4.判断是否有重复秒杀(调用dubbo接口判断是否有重复订单)
        SkOrderReq skOrderReq = new SkOrderReq();
        skOrderReq.setUserId(deducteCommodityReq.getUserId());
        skOrderReq.setCommodityId(deducteCommodityReq.getCommodityId());
        SkOrderResp skOrderResp = secondKillServiceI.skOrder(skOrderReq);
        if ("0".equals(skOrderResp.getErrCode())) {
            log.info(LogFormat.formatMsg("SecondKillController.secondKill", "seco", ""));
            return new SkResponseEntity(REPEATE_MIAOSHA.getCode(), REPEATE_MIAOSHA.getMessage());
        }
        //5.减少缓存中商品的数量
        /*Long stock = redisService.decr(CommodityKey.commodityStock, "" + deducteCommodityReq.getCommodityId());
        if (stock < 0) {
            log.info(LogFormat.formatMsg("SecondKillController.secondKill", "", ""));
            return new SkResponseEntity(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
        }*/
        String orderId = SequenceHelper.getNextSequence();
        log.info(LogFormat.formatMsg("SecondKillController.secondKill", "get current thread id::" + Thread.currentThread().getId() + "--" + orderId, ""));
        deducteCommodityReq.setSkOrderId(orderId);
        //6.往消息队列放数据
        TransactionSendResult transactionSendResult = transactionProducer.sendTransMessage(Const.ROCKET_MQ_TOPIC_KS_SK, null, Const.ROCKET_KS_ORDER_KEY + orderId, JSON.toJSONString(deducteCommodityReq));
        SkResponseEntity skResponseEntity = new SkResponseEntity(SUCCESS.getCode(), SUCCESS.getMessage());
        skResponseEntity.setSkOrderId(orderId);
        return skResponseEntity;
    }

    @RequestMapping("testRedis")
    public ResponseEntity testRedis() {
        return new ResponseEntity(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @RequestMapping("testAop")
    public ResponseEntity testAop() {
        return commodityServiceI.testAop();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++) {
            executorService.execute(new MyTask());
        }

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(new SnowFlakeUtil(0, 0).nextId());
        }

        public MyTask() {
        }
    }
}
