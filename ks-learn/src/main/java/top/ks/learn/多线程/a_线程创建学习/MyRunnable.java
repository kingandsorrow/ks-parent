package top.ks.learn.多线程.a_线程创建学习;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("start new thread!");
    }
}
