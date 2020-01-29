package top.ks.cy.web.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {
    private AtomicInteger a = new AtomicInteger(0);

    private AtomicInteger b = new AtomicInteger(0);

    @RequestMapping(value = "/downloadFile")
    public String downloads(HttpServletResponse response) throws Exception {
        String path = "/Users/birongjun/Downloads/";
        String fileName = "ks-cy-web-1.0.0-SNAPSHOT.jar";
        //1、设置response 响应头
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path, fileName);
        //2、 读取文件--输入流
        InputStream input = new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int index = 0;
        //4、执行 写出操作
        while ((index = input.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }

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
        for (String s : strs.split(",")) {
        }
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

    @RequestMapping(value = "/downExcelFile")
    public String downExcelFile(HttpServletResponse response) throws Exception {
        String path = "/Users/birongjun/Downloads/";
        String fileName = "1.xlsx";
        String filePreName = "部門卡片模版導出";
        String afterHostName = ".xlsx";
//        String chromeName1 = URLEncoder.encode(filePreName, "UTF-8");
//        String chromeName1 = new String(filePreName.getBytes("gbk"), "ISO8859-1");
        String chromeName1 = new String(filePreName.getBytes("utf-8"), "iso8859-1");
        ServletOutputStream outputStream = response.getOutputStream();
        //1、设置response 响应头
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/msexcel;charset=utf-8");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + chromeName1 + afterHostName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.close();
        return null;
    }

}
