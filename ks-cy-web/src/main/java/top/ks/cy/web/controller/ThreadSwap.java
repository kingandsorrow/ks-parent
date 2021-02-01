package top.ks.cy.web.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadSwap implements Runnable {
    private AtomicInteger integer;

    public ThreadSwap(AtomicInteger integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        while (true) {
            integer.addAndGet(1);
            log.info(String.format("birjc ThreadSwap.run:: %s, %s", Thread.currentThread().getName() + " integer is.." + integer, ""));
            Thread.yield(); //
        }
    }
}
