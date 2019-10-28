package top.ks.commodity.provider.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <b>类名称:</b>KsCommodityProvider$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/3/20
 */
@SpringBootApplication(scanBasePackages = "top.ks")
@MapperScan("top.ks.commodity.database.mapper")
@SpringBootConfiguration
@EnableAspectJAutoProxy
public class KsCommodityProvider {

    public static void main(String[] args) {
        SpringApplication.run(KsCommodityProvider.class, args);
    }

}
