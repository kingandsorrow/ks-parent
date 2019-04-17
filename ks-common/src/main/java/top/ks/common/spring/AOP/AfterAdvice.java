package top.ks.common.spring.AOP;

import java.lang.reflect.Method;

/**
 * <b>类名称:</b>AfterAdvice$<br/>
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
public class AfterAdvice implements Advice {

    private MethodInvocation methodInvocation;

    private Object bean;

    public AfterAdvice(MethodInvocation methodInvocation, Object bean) {
        this.methodInvocation = methodInvocation;
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(bean, args);
        methodInvocation.invoke();
        return null;

    }
}
