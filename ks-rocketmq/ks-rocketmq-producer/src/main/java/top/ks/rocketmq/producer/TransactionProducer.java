package top.ks.rocketmq.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ks.common.util.LogFormat;
import top.ks.rocketmq.listener.OrderListenerImpl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <b>类名称:</b>TransactionProducer$<br/>
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
public class TransactionProducer {

    private static final Log log = LogFactory.getLog(TransactionProducer.class);

    @Value("${default.group.name}")
    private String producerGroupName;
    @Value("${name.server.addr}")
    private String nameServerAddr;

    private TransactionMQProducer producer;
    @Resource
    private OrderListenerImpl orderListener;

    @PostConstruct
    public void init() throws Exception {
        log.info(LogFormat.formatMsg("DefaultProducer.init", "DefaultProducer start...", ""));
        producer = new TransactionMQProducer();
        producer.setProducerGroup(producerGroupName);
        //指定 NameServer 地址
        producer.setNamesrvAddr(nameServerAddr);
        producer.setTransactionListener(orderListener);
        //初始化 SpringProducer，整个应用生命周期内只需要初始化一次
        producer.start();
    }

    @PreDestroy
    public void destroy() {
        log.info(LogFormat.formatMsg("DefaultProducer.destroy", "DefaultProducer destroy...", ""));

        producer.shutdown();

        log.info(LogFormat.formatMsg("DefaultProducer.destroy", "DefaultProducer stop over...", ""));
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :发送事务消息
     * @author : brj
     * @CreateDate : 2019/4/2 15:14
     */
    public TransactionSendResult sendTransMessage(String topic, String tags, String keys, String body) throws MQClientException, UnsupportedEncodingException {
        byte[] byteBody = body.getBytes(RemotingHelper.DEFAULT_CHARSET);
        Message msg = new Message(topic, tags, keys, byteBody);
        TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, null);
        return sendResult;
    }

}
