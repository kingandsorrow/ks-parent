package top.ks.client.provider.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <b>类名称:</b>KsSsoProvider$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/12<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/12
 */
@SpringBootApplication(scanBasePackages = "top.ks")
@MapperScan("top.ks.client.provider.database.mapper")
@EnableScheduling
public class KsClientProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(KsClientProvider.class)
                .web(false)
                .run(args);
    }
}