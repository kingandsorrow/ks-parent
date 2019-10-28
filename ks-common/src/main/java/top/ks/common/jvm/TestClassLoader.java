package top.ks.common.jvm;

import top.ks.common.util.ToolUtil;

import java.lang.reflect.Method;
import java.util.Date;

public class TestClassLoader extends ClassLoader {


    protected TestClassLoader(ClassLoader parent) {
        super(parent);
    }

    public String say(int a) {
        return a + "";
    }

    public String sayStr(String b) {
        return b + "";
    }

    public String sayStrD(Date b) {
        return b.getTime() + "";
    }

    public static void main(String[] args) {

        Method method = ToolUtil.getMethod(TestClassLoader.class, "say");

        Class param = method.getParameterTypes()[0];
        long start1 = System.currentTimeMillis();
        System.out.println(ToolUtil.isWrapClass(param));
        long start2 = System.currentTimeMillis();
        System.out.println(start2 - start1);
        long start3 = System.currentTimeMillis();
        System.out.println(start3 - start2);
        Method method1 = ToolUtil.getMethod(TestClassLoader.class, "sayStrD");
        Class param1 = method1.getParameterTypes()[0];

    }
}
