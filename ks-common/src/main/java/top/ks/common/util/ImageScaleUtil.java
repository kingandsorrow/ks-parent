package top.ks.common.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;

/**
 * @描述:
 * @文件名: 图片压缩工具
 * @创建人: jason
 * @创建时间: 2017/9/21 / 下午2:44
 * @修改人: jason
 * @修改备注: Copyright 北京和信金谷科技有限公司 2017/9/21
 */
public class ImageScaleUtil {


    private static Log log = LogFactory.getLog(ImageScaleUtil.class);

    public static String compress(String path, double number, String suffix) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            String prefirx = FilenameUtils.getFullPath(path);
            String fileName = FilenameUtils.getBaseName(path);
            String fileType = FilenameUtils.getExtension(path);
            if (Strings.isEmpty(suffix)) {
                suffix = "";
            }
            String outPath = prefirx + fileName + suffix + "." + fileType;
            //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
            try {

                log.info(LogFormat.formatMsg("ImageScaleUtil.compress", "origin size=" + String.format("%.1f K", imgFile.length() / 1024.0), ""));
                if (imgFile.length() / 1024.0 > 200) {
                    Thumbnails.of(imgFile).scale(1f).outputQuality(number).toFile(outPath);
                }
                File outFile = new File(outPath);
                log.info(LogFormat.formatMsg("ImageScaleUtil.compress", "compress size=" + String.format("%.1f K", outFile.length() / 1024.0), ""));
                log.info(LogFormat.formatMsg("ImageScaleUtil.compress,compress success", "path=" + path + ",outPath=" + outPath, ""));
                return outPath;
            } catch (IOException e) {
                log.info(LogFormat.formatMsg("ImageScaleUtil.compress,compress error", "path=" + path + ",outPath=" + outPath, ""));
                e.printStackTrace();
            }

        } else {
            log.info(LogFormat.formatMsg("ImageScaleUtil.compress,file not found", "path=" + path, ""));
        }
        return null;
    }


    public static String scale(String path, double number, String suffix) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            String prefirx = FilenameUtils.getFullPath(path);
            String fileName = FilenameUtils.getBaseName(path);
            String fileType = FilenameUtils.getExtension(path);
            if (Strings.isEmpty(suffix)) {
                suffix = "";
            }
            String outPath = prefirx + fileName + suffix + "." + fileType;
            //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
            try {
                Thumbnails.of(path).scale(number).toFile(outPath);//按比例缩小
                File outFile = new File(outPath);
                log.info(LogFormat.formatMsg("ImageScaleUtil.scale", "scale size=" + String.format("%.1f K", outFile.length() / 1024.0), ""));
                log.info(LogFormat.formatMsg("ImageScaleUtil.scale,scale success", "path=" + path + ",outPath=" + outPath, ""));
                return outPath;
            } catch (IOException e) {
                log.info(LogFormat.formatMsg("ImageScaleUtil.scale,scale error", "path=" + path + ",outPath=" + outPath, ""));
                e.printStackTrace();
            }

        } else {
            log.info(LogFormat.formatMsg("ImageScaleUtil.scale,file not found", "path=" + path, ""));
        }
        return null;
    }


    public static void main(String[] args) {

        String path = "/Users/jason/Downloads/Desktop/aaa.jpg";
//        System.out.println(FilenameUtils.getFullPath(path));
//        System.out.println(FilenameUtils.getExtension(path));
//        System.out.println(FilenameUtils.getBaseName(path));
        compress(path, 0.2f, "_compress");
        scale(path, 0.2f, "_thumb");

    }
}
