package top.ks.commodity.provider.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
 * Copyright 西安创意 2019/3/20
 */
@SpringBootApplication(scanBasePackages = "top.ks.commodity")
@MapperScan("top.ks.commodity.database.mapper")
@SpringBootConfiguration
public class KsCommodityProvider {

    public static void main(String[] args) {
        SpringApplication.run(KsCommodityProvider.class, args);
    }

}
