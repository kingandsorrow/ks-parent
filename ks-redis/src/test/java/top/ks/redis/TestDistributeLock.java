package top.ks.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.common.util.SequenceHelper;
import top.ks.redis.distributelock.DistributedLock;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static top.ks.redis.LockKey.lockKey;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestDistributeLock {
    @Resource
    private DistributedLock distributedLock;

    private static int a = 10;

    @Test
    public void test() throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, 5, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        String key = "dlKey";

        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String value = SequenceHelper.getNextSequence();
                    boolean lockFlag = distributedLock.lock(lockKey, key, value, 20L, 50000L);
                    if (!lockFlag) {
                        System.out.println("mei huo qu suo ");
                        return;
                    }
                    a--;
                    System.out.println("Thread current is.." + Thread.currentThread().getName() + "-- a is.." + a);
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        distributedLock.unLock(lockKey, key, value);
                    }
                }
            });
        }
        while (poolExecutor.getPoolSize() != 0) {

        }
    }
}
