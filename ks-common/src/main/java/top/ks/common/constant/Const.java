package top.ks.common.constant;

import java.io.Serializable;

/**
 * <b>类名称:</b>Const$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/14<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/14
 */
public class Const implements Serializable {

    public final static String CODE_DEFAULT = "123456";
    //0 微信 1手机号 2创意登录
    public final static Byte IDENTITY_TYPE_ZERO = 0;

    public final static Byte IDENTITY_TYPE_ONE = 1;

    public final static Byte IDENTITY_TYPE_TWO = 2;

    public final static String SSO_TOKEN_KEY_PRE = "sso_token";

    public static final String REDIRECT_URL = "redirect_url";

    public final static String TOKEN = "token";
    public static final String SESSION_REDIRECT_URL = "session_redirect_url";
    public static final String MAIN_DOMAIN = "ks.com";
    //redis key链接符号
    public final static String REDIS_CONCAT_SIGN = "_";
    //秒杀订单 topic
    public final static String ROCKET_MQ_TOPIC_KS_SK = "ks_order_topic";

    public final static String ROCKET_KS_ORDER_KEY = "order_key:";
    public static final String DIS_LIMIT_KEY = "ks_limit_key";

    /**
     * sso sessionid, between browser and sso-server (web + token client)
     */
    public static final String SSO_TOKEN = "sso_token";


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

    public final static String ROCKETMQ_NAME_ADDRESS = "192.168.1.23:9876";


    public final static String ROCKET_MQ_TOPIC_KS = "ks_core_topic";
}
