package top.ks.common.conf;


/**
 * conf
 *
 * @author xuxueli 2018-04-02 19:18:04
 */
public class Conf {

    /**
     * sso sessionid, between browser and sso-server (web + token client)
     */
    public static final String SSO_TOKEN = "sso_token";


    /**
     * redirect url (web client)
     */
    public static final String REDIRECT_URL = "redirect_url";

    /**
     * sso user, request attribute (web client)
     */
    public static final String SSO_USER = "sso_user";


    /**
     * sso server address (web + token client)
     */
    public static final String SSO_SERVER = "sso_server";

    /**
     * login url, server relative path (web client)
     */
    public static final String SSO_LOGIN = "/login";
    /**
     * logout url, server relative path (web client)
     */
    public static final String SSO_LOGOUT = "/logout";


    /**
     * logout path, client relatice path
     */
    public static final String SSO_LOGOUT_PATH = "SSO_LOGOUT_PATH";

    /**
     * excluded paths, client relatice path, include path can be set by "filter-mapping"
     */
    public static final String SSO_EXCLUDED_PATHS = "SSO_EXCLUDED_PATHS";

    public final static String SSO_CHECK_LOGIN_FAIL = "0501";

    public final static String SSO_CHECK_LOGIN_FAIL_MSG = "用户未登陆";

    public final static String ROCKETMQ_NAME_ADDRESS  = "192.168.1.23:9876";


    public final static String ROCKET_MQ_TOPIC_KS  = "ks_core_topic";


}
