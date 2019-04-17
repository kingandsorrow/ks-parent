package top.ks.sso.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>类名称:</b>KsWebApplication$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/1<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br   />
 * <p>
 * Copyright 西安创意 2018/8/1
 */
@SpringBootApplication(scanBasePackages = "top.ks")
@SpringBootConfiguration
//@ImportResource("classpath*:spring/*.xml")
public class KsClientWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsClientWebApplication.class, args);

    }
}
