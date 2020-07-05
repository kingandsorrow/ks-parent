package top.ks.oss.web.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

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
@SpringBootApplication(scanBasePackages = "top.ks")
@SpringBootConfiguration
@MapperScan({"top.ks.oss.provider.database.mapper", "top.ks.sso.provider.database.mapper"})
@ImportResource("classpath*:spring/*.xml")
public class KsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsWebApplication.class, args);
    }
}
