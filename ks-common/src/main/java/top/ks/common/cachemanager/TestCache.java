package top.ks.common.cachemanager;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestCache {

    @Test
    public void testPutCache() throws InterruptedException {

        //线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        CacheManager cacheManager = CacheManager.getCacheManagerInstance();
        cacheManager.init(30000);
        for (int i = 0; i < 80; i++) {
            int arg = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setAge(arg + 1);
                    userEntity.setName(arg + "--" + Thread.currentThread().getName());
                    try {
                        cacheManager.put(arg + "-key", userEntity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(3000);
        UserEntity userEntity = (UserEntity) cacheManager.get("5-key");
        System.out.println(JSON.toJSON(cacheManager));

    }

    class MyTask implements Runnable {

        @Override
        public void run() {

        }
    }
}
