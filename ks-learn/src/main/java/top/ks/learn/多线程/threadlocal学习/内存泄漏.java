package top.ks.learn.多线程.threadlocal学习;

public class 内存泄漏 {

    public void leak() {
        ThreadLocal<Byte[]> threadLocal = new ThreadLocal<>();
        threadLocal.set(new Byte[4096 * 1024]);
    }
}
