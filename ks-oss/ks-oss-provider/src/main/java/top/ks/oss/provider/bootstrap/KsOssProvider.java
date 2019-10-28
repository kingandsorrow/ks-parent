package top.ks.oss.provider.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

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
@MapperScan("top.ks.oss.provider.database.mapper")
@ImportResource(locations = {"classpath*:spring/*.xml"})
public class KsOssProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(KsOssProvider.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
