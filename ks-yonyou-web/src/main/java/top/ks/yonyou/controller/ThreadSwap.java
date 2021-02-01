package top.ks.yonyou.controller;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSwap implements Runnable {
    private AtomicInteger integer;

    public ThreadSwap(AtomicInteger integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        while (true) {
            integer.addAndGet(1);
            Thread.yield(); //让出CPU资源
        }
    }
}
