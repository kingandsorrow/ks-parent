package top.ks.learn.多线程.d_手写线程池;

import top.ks.learn.多线程.b_线程池创建学习.KsRunable;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {

    public static void main(String[] args) {
        KsThreadPoolExecutor ksThreadPoolExecutor = new KsThreadPoolExecutor(5, 10, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            ksThreadPoolExecutor.execute(new KsRunable("" + i));
        }
        while (!ksThreadPoolExecutor.isTerminated()) {

        }
        
    }
}
