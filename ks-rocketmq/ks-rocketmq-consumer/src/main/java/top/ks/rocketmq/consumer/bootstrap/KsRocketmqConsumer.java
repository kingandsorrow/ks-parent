package top.ks.rocketmq.consumer.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * <b>类名称:</b>KsRocketmqConsumer$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/2<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/2
 */
@SpringBootApplication(scanBasePackages = "top.ks")
public class KsRocketmqConsumer {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplicationBuilder(KsRocketmqConsumer.class)
                .web(false)
                .run(args);
    }
}
