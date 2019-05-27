package top.ks.test;


import org.junit.Test;
import top.ks.commodity.database.mapper.SkCommodityMapper;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCommidity {
    @Resource
    private SkCommodityMapper skCommodityMapper;

    @Test
    public void testCommodity() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++) {
            executorService.execute(new MyTask());
        }
    }

    class MyTask implements Runnable {

        @Override
        public void run() {
            int row = skCommodityMapper.deducteCommodity("2019410172440");
            System.out.println("Thread-id:" + Thread.currentThread().getId() + " row is.." + row);
        }
    }
}
