
package top.ks.sso.provider.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ks.common.util.JedisUtil;

//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;


/**
 * <b>类名称:</b>CommonConfig$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/6<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/10/6
 */

@Configuration
public class CommonConfig implements CommandLineRunner {

    @Value("${sso.redis.address}")
    private String ssoRedisAddress;
    @Value("${sso.redis.password}")
    private String ssoRedisPassword;


    /*@Bean
    public void init() {

        // sso, redis init
        JedisUtil.init(ssoRedisAddress, ssoRedisPassword);
    }
*/
    /*@Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }*/

    @Override
    public void run(String... args) throws Exception {
        JedisUtil.init(ssoRedisAddress, ssoRedisPassword);
    }
}

