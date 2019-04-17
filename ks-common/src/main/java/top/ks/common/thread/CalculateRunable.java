package top.ks.common.thread;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.ks.framework.util.LogFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <b>类名称:</b>CalculateRunable$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/18<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/18
 */
public class CalculateRunable implements Runnable {
    private static final Log log = LogFactory.getLog(CalculateRunable.class);

    private static volatile List<Integer> pendCals = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));

    private Calculate calculate;
    private static Object lock = "112312";

    private static Lock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        while (CollectionUtil.isNotEmpty(pendCals)) {
            reentLockCal();
            //synchonizedCal();
            try {
                Thread.currentThread().sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void reentLockCal() {
        reentrantLock.lock();
        try {
            if (CollectionUtil.isEmpty(pendCals)) {
                return;
            }
            Integer preCal = pendCals.get(0);
            Integer calResult = preCal * 3;
            calculate.getCalResult().put(preCal + "", calResult);
            pendCals.remove(preCal);
            System.out.println(Thread.currentThread().getName() + "计算.." + preCal + "结果.." + calResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void synchonizedCal() {
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
    }


    public CalculateRunable(Calculate calculate) {
        this.calculate = calculate;
    }
}
