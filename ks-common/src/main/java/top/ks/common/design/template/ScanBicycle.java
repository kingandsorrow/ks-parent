package top.ks.common.design.template;

public class ScanBicycle extends AbstractClass {
    @Override
    public void unlock() {
        System.out.println("扫码开锁");
    }

    @Override
    public void ride() {
        System.out.println("骑起来很拉风");
    }

    @Override
    public void lock() {
        System.out.println("上锁");
    }

}
