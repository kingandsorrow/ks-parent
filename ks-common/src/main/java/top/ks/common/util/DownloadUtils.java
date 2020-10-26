package top.ks.common.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Slf4j
public class DownloadUtils {

    public final static String COMPLETE_PATH = "COMPLETE_PATH";
    public final static String RELATIVE_PATH = "RELATIVE_PATH";

    /**
     * 下载文件到本地
     *
     * @param urlString
     * @param filename
     * @throws Exception
     * @author sun
     * @date 2018年3月25日 上午11:01:05
     */
    public static void dUrlImg(String urlString, String filename) {
        try {
            URL url = new URL(urlString);// 构造URL
            URLConnection con = url.openConnection();// 打开连接
            InputStream is = con.getInputStream();// 输入流
            String code = con.getHeaderField("Content-Encoding");
            if ((null != code) && code.equals("gzip")) {
                GZIPInputStream gis = new GZIPInputStream(is);
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流
                OutputStream os = new FileOutputStream(filename);
                // 开始读取
                while ((len = gis.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                // 完毕，关闭所有链接
                gis.close();
                os.close();
                is.close();
            } else {
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流
                OutputStream os = new FileOutputStream(filename);
                // 开始读取
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                // 完毕，关闭所有链接
                os.close();
                is.close();
            }
        } catch (Exception e) {
            log.error(String.format("birjc DownloadUtils.dUrlImg:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2020-10-19 23:25
     */
    public static Map<String, String> generFilePath(String preFix) {
        String yearMonthStr = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        String hourMinStr = DateUtil.format(new Date(), "HHmm");
        String intStr = "0" + RandomUtil.randomInt(0, 9) + "";
        String file1 = preFix + yearMonthStr + File.separator + hourMinStr + File.separator + intStr + File.separator;
        String relatePath = File.separator + yearMonthStr + File.separator + hourMinStr + File.separator + intStr + File.separator;
        String file2 = preFix + relatePath;
        String filePath = preFix.endsWith(File.separator) ? file1 : file2;
        if (!FileUtil.exist(filePath)) {
            FileUtil.mkdir(filePath);
        }
        Map<String, String> map = new HashMap<>();
        map.put(COMPLETE_PATH, filePath);
        map.put(RELATIVE_PATH, relatePath);
        return map;
    }

    public static void main(String[] args) {
        Map map = generFilePath("/Users/birongjun/Downloads");
        System.out.println(JSON.toJSON(map));
    }

}
