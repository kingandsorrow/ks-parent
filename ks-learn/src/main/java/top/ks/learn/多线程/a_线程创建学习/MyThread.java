package top.ks.learn.多线程.a_线程创建学习;

public class MyThread extends Thread {

    @Override
    public void run() {
        int i = 10 / 0;
        System.out.println("start new thread!");
    }
}
