package top.ks.sso.core.help;

import top.ks.common.util.SpringHelper;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.util.LoginUtil;

public class LoginFactory {

    public final static String CLIENT_LOGIN_ID = "clientLoginServiceI";

    public final static String OSS_LOGIN_ID = "ossLoginServiceI";
    //loginFrom参数
    public final static String LOGIN_FROM_PAR = "loginFrom";

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获取登录实现类
     * @author : birjc
     * @CreateDate : 2019-11-06 20:33
     */
    public static LoginServiceI getLoginServiceI(int loginFrom) {
        switch (loginFrom) {
            case LoginUtil.LOGIN_FROM_ZERO:
                return SpringHelper.getBean(CLIENT_LOGIN_ID);
            case LoginUtil.LOGIN_FROM_ONE:
                return SpringHelper.getBean(OSS_LOGIN_ID);
            default:
                return SpringHelper.getBean(CLIENT_LOGIN_ID);
        }
    }

}
