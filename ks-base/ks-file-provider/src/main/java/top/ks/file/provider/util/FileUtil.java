package top.ks.file.provider.util;

import lombok.extern.slf4j.Slf4j;
import top.ks.common.util.LogFormat;

import java.io.InputStream;

@Slf4j
public class FileUtil {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :关闭InputStream
     * @author : birjc
     * @CreateDate : 2020-04-22 21:03
     */
    public static void closeInput(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                log.error("system exception:", e);
                log.error(LogFormat.formatMsg("FileUtil.closeInput", "system error::" + e.getMessage(), ""));
            }
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 追加文件的后缀 如 执行结果为 .jar
     * @author : birjc
     * @CreateDate : 2020-04-22 21:32
     */
    public static String preAppendDoc(String fileType) {
        if (fileType.length() > 0 && fileType.charAt(0) != '.') {
            return "." + fileType;
        }
        return fileType;
    }
}
