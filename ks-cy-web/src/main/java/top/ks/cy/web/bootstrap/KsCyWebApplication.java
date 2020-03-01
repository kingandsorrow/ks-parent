package top.ks.cy.web.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <b>类名称:</b>KsWebApplication$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/1<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2018/8/1
 */


@EnableTransactionManagement
@MapperScan("top.ks.cy.web.database.mapper")
@SpringBootApplication(scanBasePackages = "top.ks.cy.web")
@SpringBootConfiguration
public class KsCyWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(KsCyWebApplication.class, args);
    }
}
