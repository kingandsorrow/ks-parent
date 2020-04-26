package top.ks.file.provider.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/*import org.springframework.boot.WebApplicationType;*/

/**
 * <b>类名称:</b>KsFileProvider<br/>
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
@ImportResource(locations = {"classpath*:spring/*.xml"})
public class KsFileProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(KsFileProvider.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
