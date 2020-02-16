package top.ks.sso.provider.factory;

import org.springframework.stereotype.Component;
import top.ks.common.util.SpringHelper;
import top.ks.sso.consumer.util.LoginUtil;
import top.ks.sso.provider.database.service.LoginService;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class LoginWayFactory {

    public final static String ACCOUNT_LOGIN_SERVICE = "accountLoginServiceImpl";
    @Resource
    private Map<String, LoginService> loginServiceMap;


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获取登录实现类
     * @author : birjc
     * @CreateDate : 2019-11-06 20:33
     */
    public LoginService getLoginService(int loginWay) {
        return loginServiceMap.get(ACCOUNT_LOGIN_SERVICE);
    }
}
