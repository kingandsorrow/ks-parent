package top.ks.common.redis;

/**
 * <b>类名称:</b>SsoKey$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/18<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/18
 */
public class SsoKey extends BasePrefix {

    private SsoKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SsoKey ssoUserToken = new SsoKey(60 * 60 * 24 * 14, "sut");
    public static SsoKey commodityDetail = new SsoKey(60, "cd");

    public static SsoKey commodityStock = new SsoKey(0, "cs");
}
