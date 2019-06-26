package top.ks.common.dynamicproxy;

/**
 * 供应商
 */
public class Vendor implements Sell {
    @Override
    public void sell() {
        System.out.println("in sell method");
    }

    @Override
    public void add() {
        System.out.println(" add method");
    }
}
