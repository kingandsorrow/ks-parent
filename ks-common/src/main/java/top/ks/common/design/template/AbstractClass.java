package top.ks.common.design.template;

public abstract class AbstractClass {
    protected boolean isNeedUnlock = true;  // 默认需要开锁

    protected abstract void unlock();

    protected abstract void ride();

    protected abstract void lock();

    /**
     * 钩子方法，子类可实现
     *
     * @param isNeedUnlock
     */
    protected void isNeedUnlock(boolean isNeedUnlock) {
        this.isNeedUnlock = isNeedUnlock;
    }

    public final void pay() {
        System.out.println("结算");
    }

    public final void use() {
        if (isNeedUnlock) {
            unlock();
            ride();
            lock();
            pay();
        } else {
            ride();
        }
    }
}
