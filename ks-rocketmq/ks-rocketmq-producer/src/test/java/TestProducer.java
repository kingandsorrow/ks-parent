import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * <b>类名称:</b>TestProducer$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/3<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/3
 */
public class TestProducer {


    @Test
    public void testProducer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("default-group-name");
        //指定 NameServer 地址
        producer.setNamesrvAddr("192.168.1.18:9876");
        //初始化 SpringProducer，整个应用生命周期内只需要初始化一次
        producer.start();
        Message msg = new Message("ks-order-topic" /* Topic */,
                null /* Tag */,
                ("Hello RocketMQ test1").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        producer.send(msg);
        producer.shutdown();
    }
}
