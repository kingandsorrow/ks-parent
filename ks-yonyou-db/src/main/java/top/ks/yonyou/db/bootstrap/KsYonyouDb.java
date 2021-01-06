package top.ks.yonyou.db.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@SpringBootConfiguration
public class KsYonyouDb {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(KsYonyouDb.class, args);
    }
}
