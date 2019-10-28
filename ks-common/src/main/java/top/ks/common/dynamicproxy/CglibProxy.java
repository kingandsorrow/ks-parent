package top.ks.common.dynamicproxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.ks.common.clone.Student;

import java.lang.reflect.Method;

/**
 * <b>类名称:</b>CglibProxy<br/>
 * <b>类注释:</b>代理Cglib 这个类<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2019-06-23
 */
public class CglibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cglib.class);
        Callback callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("您好");
                //methodProxy.invoke(o, objects);
                methodProxy.invokeSuper(o, objects);
                System.out.println("再会");
                return null;
            }
        };
        enhancer.setCallback(callback);
        Cglib cglib = (Cglib) enhancer.create();
        cglib.sayHello();
    }
}
