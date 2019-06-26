package top.ks.common.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//Invocation:调用 Handler:处理器
public class DynamicProxy implements InvocationHandler {
    private Object obj;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " log before");
        Object result = method.invoke(obj, args);
        System.out.println(method.getName() + " log after");
        return result;
    }

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }
}
