package top.ks.common.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalExample implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> threadlocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    private static final ThreadLocal<SimpleDateFormat> simpledates = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };

    @Override
    public void run() {
        System.out.println("Thread Name is.." + Thread.currentThread().getName() + " default  foramt " + threadlocal.get().toPattern());

        try {
            Thread.currentThread().sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadlocal.set(new SimpleDateFormat());
        System.out.println("Thread Name is.." + Thread.currentThread().getName() + " formatter ==" + threadlocal.get().toPattern());

    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, 10, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
        ThreadLocalExample threadLocalExample = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(threadLocalExample);
            poolExecutor.execute(thread);
        }
        while (poolExecutor.getPoolSize() != 0) {

        }
    }
}
