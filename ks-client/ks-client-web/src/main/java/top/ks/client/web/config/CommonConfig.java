
package top.ks.sso.web.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.ks.common.constant.Const;
import top.ks.sso.core.filter.SsoWebFilter;
import top.ks.sso.web.interceptor.LoginInterceptor;

import javax.annotation.Resource;

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
public class CommonConfig implements DisposableBean {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Value("${sso.redis.address}")
    private String ssoRedisAddress;
    @Value("${sso.redis.password}")
    private String ssoRedisPassword;

    @Value("${sso.server}")
    private String ssoServer;

    @Value("${sso.logout.path}")
    private String ssoLogoutPath;

    @Value("${sso.excluded.paths}")
    private String ssoExcludedPaths;
    @Resource
    private SsoWebFilter ssoWebFilter;

    /*@Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//        config.addExposedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }*/
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("http://www.ks.com:8000");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//        config.addExposedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        CorsFilter corsFilter = new CorsFilter(configSource);
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter);
        registration.setOrder(0);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean ssoFilterRegistration() {
        // xxl-sso, redis init

        // xxl-sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("SsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(ssoWebFilter);
        registration.addInitParameter(Const.SSO_SERVER, ssoServer);
        registration.addInitParameter(Const.SSO_LOGOUT_PATH, ssoLogoutPath);
        registration.addInitParameter(Const.SSO_EXCLUDED_PATHS, ssoExcludedPaths);
        return registration;
    }

    @Override
    public void destroy() throws Exception {
    }
}

