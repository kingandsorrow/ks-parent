package top.ks.common.basic;

/**
 * <b>类名称:</b>StatusCodeConst$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/1<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br                               />
 * <p>
 * Copyright 西安创意 2018/8/1
 */
public class StatusCodeConst {

    public final static String NO_SUCH_SERVICEI = "";

    public final static String NO_SUCH_METHOD = "";

    public final static String SYSTEM_ERROR = "9999";

    public final static String DATA_NOT_EXIST = "9990";

    public final static String PARAMS_NULL = "9990";

    public final static String LOGIN_FAIL = "9001";

    public final static String TOKEN_EXPIRE = "9003";

    public final static String SUCCESS = "0000";

    public final static String LOGIN_EXPIRE = "0401";

    public final static String PHONE_ERROR  = "9901";

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(s.replace("a", "e"));
        System.out.println(s);
    }
}
