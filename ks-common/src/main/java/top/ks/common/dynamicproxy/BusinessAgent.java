package top.ks.common.dynamicproxy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 代理商
 * 代理类的优点是可以隐藏为委托类的实现，并且可以在不修改委托类的情况下做一些额外的处理
 */
public class BusinessAgent implements Sell {

    private Vendor vendor;
    private AtomicInteger count = new AtomicInteger(10);

    private int unsafeCount = 10;

    @Override
    public void sell() {
        vendor.sell();
        count.decrementAndGet();
        //unsafeCount--;
        System.out.println(count);
    }

    @Override
    public void add() {
        vendor.sell();
    }

    public BusinessAgent(Vendor vendor) {
        this.vendor = vendor;
    }
}
