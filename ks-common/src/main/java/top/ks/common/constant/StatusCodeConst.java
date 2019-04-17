package top.ks.common.constant;

import java.io.Serializable;

/**
 * <b>类名称:</b>StatusCodeConst$<br/>
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
public class StatusCodeConst implements Serializable {

    public final static String PARAMS_NULL = "9001";

    public final static String PHONE_IS_WRONG = "9002";

    public final static String CODE_IS_WRONG = "9003";

    public final static String DATA_NOT_EXSIT = "9004";

    public static final String SYSTEM_ERROR = "9999";


    public final static String SUCCESS = "0000";
    public static final String NOT_LOGIN = "0401";
}
