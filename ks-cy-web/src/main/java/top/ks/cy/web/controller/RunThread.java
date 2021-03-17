package top.ks.cy.web.controller;

import top.ks.common.util.ToolUtil;

import java.util.Date;

public class RunThread implements Runnable {
    @Override
    public void run() {
        System.out.println("start....");
        while (true) {
            System.out.println("handle some.." + ToolUtil.getDateStr(new Date()));
        }
    }
}
