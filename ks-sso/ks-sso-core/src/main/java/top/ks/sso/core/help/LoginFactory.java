package top.ks.sso.core.help;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.ks.common.util.LogFormat;
import top.ks.sso.consumer.LoginServiceI;

public class LoginFactory {

    public final static String CLIENT_LOGIN_ID = "clientLoginServiceI";

    public final static String OSS_LOGIN_ID = "ossLoginServiceI";
    //loginFrom参数
    public final static String LOGIN_FROM_PAR = "loginFrom";

    private static final Log log = LogFactory.getLog(LoginFactory.class);

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获取登录接口（Oss Client）
     * @author : birjc
     * @CreateDate : 2019-12-01 21:41
     */
    public static LoginServiceI getLoginServiceI(String cookieToken) throws ClassNotFoundException {
        long startTime = System.currentTimeMillis();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("ks-sso-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("172.20.47.111:2181");
        registry.setProtocol("zookeeper");

        ReferenceConfig<LoginServiceI> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(application);
        referenceConfig.setRegistry(registry);
        referenceConfig.setGroup("oss_login");
        referenceConfig.setInterface(LoginServiceI.class);
        referenceConfig.setVersion("1.0.0");
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        LoginServiceI loginServiceI = cache.get(referenceConfig);
        log.info(LogFormat.formatMsg("LoginFactory.getLoginServiceI", "get ServiceI is.." + (System.currentTimeMillis() - startTime), ""));
        return loginServiceI;
    }

}
