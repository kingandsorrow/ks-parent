package top.ks.common.thread;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b>类名称:</b>TestMain$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/15<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/15
 */
public class TestMain {
    //有30个数 每个数都要乘以3； 分4个线程来计算
    @Test
    public void testCalculate() throws InterruptedException {

        Calculate calculate = new Calculate();
        Thread thread1 = new Thread(new CalculateRunable(calculate));
        Thread thread2 = new Thread(new CalculateRunable(calculate));
        Thread thread3 = new Thread(new CalculateRunable(calculate));
        Thread thread4 = new Thread(new CalculateRunable(calculate));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.currentThread().sleep(10000);
        System.out.println(JSON.toJSON(calculate));
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2019/3/20 15:05
     */
    @Test
    public void testPoolCalculate() throws InterruptedException {
        Calculate calculate = new Calculate();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Object lock = "aaa";
        for (int i = 0; i < 8; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 开始执行任务..");
                    List<Integer> pendCals = calculate.getPendCals();
                    while (CollectionUtil.isNotEmpty(pendCals)) {
                        synchronized (lock) {
                            if (CollectionUtil.isEmpty(pendCals)) {
                                return;
                            }
                            Integer preCal = pendCals.get(0);
                            Integer calResult = preCal * 3;
                            calculate.getCalResult().put(preCal + "", calResult);
                            pendCals.remove(preCal);
                            System.out.println(Thread.currentThread().getName() + "计算.." + preCal + "结果.." + calResult);
                        }
                        try {
                            Thread.currentThread().sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.shutdown();
        Thread.currentThread().sleep(10000);
        System.out.println(JSON.toJSON(calculate));

    }
}
