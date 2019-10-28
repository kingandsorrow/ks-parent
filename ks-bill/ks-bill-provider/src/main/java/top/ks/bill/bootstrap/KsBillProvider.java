package top.ks.bill.bootstrap;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/*import org.springframework.boot.WebApplicationType;*/

/**
 * <b>类名称:</b>KsPermissionProvider$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/29<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/9/29
 */
@SpringBootApplication(scanBasePackages = "top.ks")
@MapperScan("top.ks.bill.provider.database.mapper")
@ImportResource(locations = {"classpath*:spring/*.xml"})
public class KsBillProvider {
    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(KsBillProvider.class)
                .web(false)
                .run(args);
    }
}
