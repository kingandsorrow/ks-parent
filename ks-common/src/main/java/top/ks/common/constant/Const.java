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
 * Copyright 西安创意 2018/12/14
 */
public class Const implements Serializable {

    public final static String CODE_DEFAULT = "1234";
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
}
