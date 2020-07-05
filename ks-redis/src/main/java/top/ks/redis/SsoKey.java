package top.ks.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
 * Copyright KS 2019/4/18
 */
public class SsoKey extends BasePrefix {

    private SsoKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    //6个小时
    public static SsoKey ssoUserToken = new SsoKey(6 * 60 * 60, "sut");
    public static SsoKey commodityDetail = new SsoKey(60, "cd");

    public static SsoKey commodityStock = new SsoKey(0, "cs");
}
