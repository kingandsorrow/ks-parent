package top.ks.common.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    @org.junit.Test
    public void staticProxy() throws InterruptedException {
        Vendor vendor = new Vendor();
        BusinessAgent businessAgent = new BusinessAgent(vendor);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    businessAgent.sell();
                }
            });
        }

    }

    @org.junit.Test
    public void testDynamicProxy() {
        Vendor vendor = new Vendor();
        DynamicProxy dynamicProxy = new DynamicProxy(vendor);
        Sell sell = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, dynamicProxy);
        sell.add();
        sell.sell();
    }

    public static void main(String[] args) {
        Vendor vendor = new Vendor();
        DynamicProxy dynamicProxy = new DynamicProxy(vendor);
        Sell sell = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, dynamicProxy);
        sell.add();
        sell.sell();
    }


}
