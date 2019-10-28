package top.ks.rocketmq.consumer.consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ks.rocketmq.consumer.listener.OrderListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * <b>类名称:</b>DefaultConsumer$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/2<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/2
 */
@Component
public class DefaultConsumer {
    private static final Log log = LogFactory.getLog(DefaultConsumer.class);
    @Value("${default.consumer.group}")
    private String consumerGroupName;
    @Value("${name.server.addr}")
    private String nameServerAddr;
    @Value("${topic.name}")
    private String topicName;

    private DefaultMQPushConsumer consumer;
    @Resource
    private OrderListener orderListener;


    @PostConstruct
    public void init() throws Exception {
        log.info("开始启动消息消费者服务...");

        //创建一个消息消费者，并设置一个消息消费者组
        consumer = new DefaultMQPushConsumer(consumerGroupName);
        //指定 NameServer 地址
        consumer.setNamesrvAddr(nameServerAddr);
        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //订阅指定 Topic 下的所有消息
        consumer.subscribe(topicName, "*");

        //注册消息监听器
        consumer.registerMessageListener(orderListener);

        // 消费者对象在使用之前必须要调用 start 初始化
        consumer.start();

        log.info("消息消费者服务启动成功.");
    }

    @PreDestroy
    public void destroy() {
        log.info("开始关闭消息消费者服务...");

        consumer.shutdown();

        log.info("消息消费者服务已关闭.");
    }

    public DefaultMQPushConsumer getConsumer() {
        return consumer;
    }

}
