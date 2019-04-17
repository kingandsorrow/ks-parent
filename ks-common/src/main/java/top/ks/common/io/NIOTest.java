package top.ks.common.io;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Date;

import static cn.hutool.core.io.IoUtil.close;

/**
 * <b>类名称:</b>NIOTest$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/11<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/11
 */
public class NIOTest {

    public final static String FILE_PATH = "D:\\data\\a.avi";

    public final static String FILE_OUT_PATH = "D:\\example\\copya.avi";

    public static void main(String[] args) throws IOException {
        //NIO实现文件复制
        nioCopyFile1();

        //普通IO实现文件复制
        ioCopyFile();

        //NIO实现文件复制(循环方式)
        nioCopyFile2();
    }

    private static void nioCopyFile1() {
        FileChannel fci = null;
        FileChannel fco = null;
        try {
            long beginTime = System.currentTimeMillis();
            fci = new FileInputStream(new File(FILE_PATH)).getChannel();
            fco = new RandomAccessFile(new File(FILE_OUT_PATH), "rw")
                    .getChannel();
            fco.transferFrom(fci, 0, fci.size());
            long endTime = System.currentTimeMillis();
            System.out.println("采用NIO FileChannel 自带方法  读取，耗时："
                    + (endTime - beginTime));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fci != null) {
                    fci.close();
                }
                if (fco != null) {
                    fco.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void nioCopyFile2() {
        FileChannel fci = null;
        FileChannel fco = null;
        try {
            long beginTime = System.currentTimeMillis();
            fci = new FileInputStream(new File(FILE_PATH)).getChannel();
            fco = new RandomAccessFile(new File(FILE_OUT_PATH), "rw")
                    .getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (fci.read(buf) != -1) {
                buf.flip();
                fco.write(buf);
                buf.clear();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("采用NIO FileChannel 循环 读取，耗时："
                    + (endTime - beginTime));
        } catch (Exception e) {
        } finally {
            try {
                if (fci != null) {
                    fci.close();
                }
                if (fco != null) {
                    fco.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :普通io复制文件
     * @author : brj
     * @CreateDate : 2019/3/12 14:18
     */
    private static void ioCopyFile() {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            long beginTime = System.currentTimeMillis();
            fis = new BufferedInputStream(new FileInputStream(new File("D:\\data\\a.avi")));
            fos = new BufferedOutputStream(new FileOutputStream(new File("D:\\example\\copya.avi")));
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("采用IO BufferedInputStream 方法  读取，耗时："
                    + (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(fos);
        }
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2019/3/12 0:06
     */
    public static void nioCN() throws IOException {
        Charset charset = Charset.forName("GBK");//Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
        CharsetDecoder decoder = charset.newDecoder();

        RandomAccessFile raf = new RandomAccessFile("D:\\data\\no-data.txt", "rw");
        FileChannel fc = raf.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        CharBuffer cb = CharBuffer.allocate(512);

        int count = fc.read(buffer);
        while (count != -1) {
            System.out.println("count = " + count);
            buffer.flip();
            decoder.decode(buffer, cb, false);
            cb.flip();
            while (cb.hasRemaining()) {
                System.out.print(cb.get());
            }
            System.out.println();
            buffer.clear();
            cb.clear();
            count = fc.read(buffer);
        }
        raf.close();
    }

    /**
     * @Method :
     * @Description :
     * @param :
     * @return : 
     * @author : brj
     * @CreateDate : 2019/3/12 16:44
     */
    public static void testSelector() throws IOException {
        Selector selector = Selector.open();
        //channel.configureBlocking(false);
        
    }
}
