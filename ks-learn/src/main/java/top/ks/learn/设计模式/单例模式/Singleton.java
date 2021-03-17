package top.ks.learn.设计模式.单例模式;

public class Singleton {
    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //synchronized同步块，控制更细的粒度
        if (instance == null) {//此层的控制允许第一个线程进入访问，避免以上情况（同步方法）每次的等待开销。
            synchronized (Singleton.class) {
                if (instance == null) {//此处校验单例是否已经被创建，确实只有一个实例的存在。
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstanceStatic() {
        return SingletonHelper.INSTANCE;
    }
}
