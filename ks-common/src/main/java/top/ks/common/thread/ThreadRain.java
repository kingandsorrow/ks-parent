package top.ks.common.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadRain implements Runnable {

    //private volatile int trainCount = 10;
    private AtomicInteger trainCount = new AtomicInteger(0);

    private ReentrantLock reentrantLock = new ReentrantLock();

    Thread thread;

    @Override
    public void run() {
        while (trainCount.get() > 0) {
            try {
                Thread.sleep(500);
                sale();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void sale() {
        if (trainCount.get() < 1) {
            trainCount.getAndIncrement();
            System.out.println(Thread.currentThread().getName() + ",出售第" + (trainCount.get()) + "张票");
        } else {
            System.out.println(Thread.currentThread().getName() + " 结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadRain threadRain = new ThreadRain();
        Thread t1 = new Thread(threadRain, "A窗口");
        Thread t2 = new Thread(threadRain, "B窗口");
        t1.start();
        t2.start();
        Thread.sleep(1000);
    }
}
