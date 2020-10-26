package top.ks.h5.web.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.ks")
@SpringBootConfiguration
@MapperScan({"top.ks.oss.provider.database.mapper", "top.ks.sso.provider.database.mapper"})
public class H5WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(H5WebApplication.class, args);
    }
}
