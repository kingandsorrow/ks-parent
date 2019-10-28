package top.ks.common.threadpool;

/**
 * <b>类名称:</b>TestThreadPool$<br/>
 * <b>类注释:</b>测试线程池<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/11<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/11
 */

public class TestThreadPool {
    public static void main(String[] args) {
        // 创建3个线程的线程池
        ThreadPool t = ThreadPool.getThreadPool(3);
        t.execute(new Runnable[]{new Task(), new Task(), new Task()});
        t.execute(new Runnable[]{new Task(), new Task(), new Task()});
        System.out.println("销毁之前.." + t);
        t.destroy();// 所有线程都执行完成才destory
        System.out.println("销毁之后.." + t);
    }

    // 任务类
    static class Task implements Runnable {
        private static volatile   int i = 1;

        @Override
        public void run() {// 执行任务
            System.out.println("任务 " + (i++) + " 完成");
        }
    }
}
