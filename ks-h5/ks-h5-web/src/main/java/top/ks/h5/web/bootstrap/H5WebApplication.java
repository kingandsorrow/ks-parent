package top.ks.h5.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.ks")
@SpringBootConfiguration
public class H5WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(H5WebApplication.class, args);
    }
}
