package top.ks.learn.多线程.a_线程创建学习;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();

        Thread runableT = new Thread(new MyRunnable());
        runableT.start();
        System.out.println("线程 执行");
    }

}
