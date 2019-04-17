package top.ks.common.spring.AOP;

import java.lang.reflect.Proxy;

/**
 * <b>类名称:</b>SimpleAop$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/14<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/14
 */
public class SimpleAop {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAop.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);
    }
}
