package top.ks.yonyou.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadController {
    @RequestMapping("/swapThread")
    public String swapThread(int num, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("模拟线程切换");
        for (int i = 0; i < num; i++) {
            new Thread(new ThreadSwap(new AtomicInteger(0)), "thread-swap" + i).start();
        }
        return "ok";
    }


}


