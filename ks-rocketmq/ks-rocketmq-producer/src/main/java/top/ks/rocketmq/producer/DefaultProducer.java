package top.ks.rocketmq.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ks.common.util.LogFormat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <b>类名称:</b>SpringProducer$<br/>
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
public class DefaultProducer {

    private static final Log log = LogFactory.getLog(DefaultProducer.class);
    @Value("${default.group.name}")
    private String producerGroupName;
    @Value("${name.server.addr}")
    private String nameServerAddr;

    private DefaultMQProducer producer;

    @PostConstruct
    public void init() throws Exception {
        log.info(LogFormat.formatMsg("DefaultProducer.init", "DefaultProducer start...", ""));

        //创建一个消息生产者，并设置一个消息生产者组
        producer = new DefaultMQProducer(producerGroupName);
        //指定 NameServer 地址
        producer.setNamesrvAddr(nameServerAddr);
        //初始化 SpringProducer，整个应用生命周期内只需要初始化一次
        producer.start();

    }

    @PreDestroy
    public void destroy() {
        log.info(LogFormat.formatMsg("DefaultProducer.destroy", "DefaultProducer destroy...", ""));

        producer.shutdown();

        log.info(LogFormat.formatMsg("DefaultProducer.destroy", "DefaultProducer stop over...", ""));
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public DefaultProducer(String producerGroupName, String nameServerAddr) {
        this.producerGroupName = producerGroupName;
        this.nameServerAddr = nameServerAddr;
    }

}
