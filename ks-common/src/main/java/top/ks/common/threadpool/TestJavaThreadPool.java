package top.ks.common.threadpool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <b>类名称:</b>TestJavaThreadPool$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/11<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/11
 */
public class TestJavaThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            threadPoolExecutor.execute(myTask);
            System.out.println("线程池中线程数目：" + threadPoolExecutor.getPoolSize() + ",队列中等待执行的任务数目：" + threadPoolExecutor.getQueue().size() + ",已执行完别的任务数目.." + threadPoolExecutor.getCompletedTaskCount());
        }
        while (threadPoolExecutor.getPoolSize() != 0) {
            System.out.println("线程池持续在跑。。");
        }
        threadPoolExecutor.shutdown();
    }

    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕..");
        }
    }

    @Test
    public void test1() {
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;
        System.out.println(-1 << COUNT_BITS);
        System.out.println(1 << COUNT_BITS);

        System.out.println((1 << COUNT_BITS) - 1);

        System.out.println(5 & CAPACITY);
    }
}
