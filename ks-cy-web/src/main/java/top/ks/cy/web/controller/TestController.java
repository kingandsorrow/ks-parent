package top.ks.cy.web.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {
    private AtomicInteger a = new AtomicInteger(0);

    private AtomicInteger b = new AtomicInteger(0);


    @RequestMapping("/testNoLock")
    public String testNoLock(String strs) throws Exception {
        String result = "";
        a.getAndIncrement();
        Thread.sleep(1000);
        result = a.toString();
        return result + "--" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss sss");
    }

    @RequestMapping("/testDistrantLock")
    public String testDistrantLock(String strs) throws Exception {
        String result = "";
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("172.20.56.136:2181", retryPolicy);
        client.start();
        for (String s :strs.split(",")){}
            //创建分布式锁, 锁空间的根节点路径为/curator/lock
            InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        mutex.acquire();
        //获得了锁, 进行业务流程
        b.getAndIncrement();
        Thread.sleep(1000);
        //完成业务流程, 释放锁
        result = b.toString();
        mutex.release();

        //关闭客户端
        client.close();
        return result + "--" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss sss");
    }
}
