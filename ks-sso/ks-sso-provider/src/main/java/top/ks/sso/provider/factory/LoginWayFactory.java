package top.ks.sso.provider.factory;

import top.ks.common.util.SpringHelper;
import top.ks.sso.consumer.util.LoginUtil;
import top.ks.sso.provider.database.service.LoginService;

public class LoginWayFactory {

    public final static String ACCOUNT_LOGIN_SERVICE = "accountLoginServiceImpl";

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获取登录实现类
     * @author : birjc
     * @CreateDate : 2019-11-06 20:33
     */
    public static LoginService getLoginService(int loginWay) {
        switch (loginWay) {
            case LoginUtil.LOGIN_WAY_ZERO:
                return SpringHelper.getBean(ACCOUNT_LOGIN_SERVICE);
            default:
                return SpringHelper.getBean(ACCOUNT_LOGIN_SERVICE);
        }
    }
}
