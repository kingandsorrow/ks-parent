package top.ks.cy.web.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.cy.web.database.mapper.UserJoinMapper;
import top.ks.cy.web.util.Excel2007Utils;
import top.ks.cy.web.util.ExcelExportData;
import top.ks.cy.web.util.ExcelField;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class TestController {
    private AtomicInteger a = new AtomicInteger(0);

    private AtomicInteger b = new AtomicInteger(0);

    @Resource
    private UserJoinMapper userJoinMapper;


    @RequestMapping(value = "/downloadFile")
    public String downloads(HttpServletResponse response) throws Exception {
        String path = "/Users/birongjun/Downloads/";
        String fileName = "采购委托关系管理列表_CreateSQL_616973480136960_jinyidemotest.sql";
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
        ByteArrayOutputStream byteArrayOutputStream = null;
        ServletOutputStream outputStream = null;
        try {
            String filePreName = userJoinMapper.selectOneName("1");
            String chromeName1 = new String(filePreName.getBytes("gbk"), "ISO8859-1");
            String afterHostName = "xlsx";
            outputStream = response.getOutputStream();
            //1、设置response 响应头
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/msexcel;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + chromeName1 + afterHostName);
            Excel2007Utils excel2007Utils = new Excel2007Utils();
            ExcelExportData excelExportData = handelExportData(chromeName1);
            byteArrayOutputStream = excel2007Utils.export2Stream(excelExportData);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            outputStream.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.flush();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return null;
    }


    private ExcelExportData handelExportData(String filePreName) {
        ExcelExportData excelExportData = new ExcelExportData();
        excelExportData.setFileName(filePreName);
        List<String[]> list = new ArrayList<>();
        String[] strs = {"部門編碼", "部門名稱(简体中文)", "部門名稱(English)", "部門名稱(繁體中文)", "所属上级", "部門性質", "負責人", "分管領導", "狀態"};
        list.add(strs);
        excelExportData.setColumnNames(list);
        List<String[]> listField = new ArrayList<>();
        String[] strFields = {"code", "name", "name2", "name3", "parent_name", "depttype_name", "principal_name", "branchleader_name", "enable"};
        listField.add(strFields);
        List<ExcelField[]> excelFields = new ArrayList<>();
        ExcelField[] excelFieldsArr = new ExcelField[9];
        excelFieldsArr[0] = handelOneExcelField("code", false, false, null, CellType.STRING, null);
        excelFieldsArr[1] = handelOneExcelField("name", false, false, null, CellType.STRING, null);
        excelFieldsArr[2] = handelOneExcelField("name2", false, false, null, CellType.STRING, null);
        excelFieldsArr[3] = handelOneExcelField("name3", false, false, null, CellType.STRING, null);
        excelFieldsArr[4] = handelOneExcelField("parent_name", false, false, null, CellType.STRING, "请输入上级编码");
        excelFieldsArr[5] = handelOneExcelField("depttype_name", false, false, null, CellType.STRING, null);
        excelFieldsArr[6] = handelOneExcelField("principal_name", false, false, null, CellType.STRING, null);
        excelFieldsArr[7] = handelOneExcelField("branchleader_name", false, false, null, CellType.STRING, null);
        excelFieldsArr[8] = handelOneExcelField("enable", false, false, null, CellType.STRING, null);
        excelFields.add(excelFieldsArr);
        excelExportData.setFields(excelFields);
        excelExportData.setFieldNames(listField);
        LinkedHashMap<String, List<Object>> dataMap = new LinkedHashMap<>();
        dataMap.put("DeptOrgVO(部门)", null);
        excelExportData.setDataMap(dataMap);
        LinkedHashMap<String, List<Map<String, Object>>> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("DeptOrgVO(部门)", null);
        excelExportData.setHeadersMap(linkedHashMap);
        return excelExportData;
    }

    private ExcelField handelOneExcelField(String code, boolean b, boolean b1, String o, CellType cellType, String o1) {
        ExcelField excelField = new ExcelField();
        excelField.setFieldName(code);
        excelField.setIsEnum(b);
        excelField.setIsNull(b1);
        excelField.setEnumString(o);
        excelField.setCellType(cellType);
        excelField.setMappingName(o1);
        return excelField;
    }
}
