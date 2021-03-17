package top.ks.cy.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ThreadController {
    @RequestMapping("/swapThread")
    public String swapThread(int num, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("模拟线程切换");
        for (int i = 0; i < num; i++) {
            new Thread(new ThreadSwap(new AtomicInteger(0)), "thread-swap" + i).start();
        }
        return "ok";
    }

    @RequestMapping("/testLeak")
    public String testLeak() {
        System.out.println("模拟内存泄漏");
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
        localVariable.set(new Byte[4096 * 1024]);// 为线程添加变量
        return "ok";
    }

    @RequestMapping("/testdeathThread")
    public void testdeathThread() {
        System.out.println("开启一个线程");
        new Thread(new RunThread()).start();
        System.out.println("结束一个线程");

    }

}


