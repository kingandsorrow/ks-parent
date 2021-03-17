package top.ks.learn.多线程.b_线程池创建学习;

import java.util.Date;

public class KsRunable implements Runnable {

    private String command;

    public KsRunable(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date() + " result:" + command);
        processCommand();
        if ("3".equals(command)) {
            int i = 10 / 0;
        }
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date() + " result:" + command);
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
