package top.ks.client.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ks.client.api.FileUploadServiceI;
import top.ks.client.api.req.UploadImgReq;
import top.ks.client.api.resp.FilepathRes;
import top.ks.common.util.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static top.ks.common.util.ResponseEntity.PARAM_ERROR;
import static top.ks.common.util.ResponseEntity.SYSTEM_ERROR;


@Service(
        version = "${dubbo.application.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        retries = -1,
        timeout = 30000

)
@Component
public class FileUploadServiceIProxy implements FileUploadServiceI {
    private final static Log log = LogFactory.getLog(FileUploadServiceIProxy.class);
    @Value("${file.path}")
    private String filePath;

    @Override
    public FilepathRes imgStrUrlStream(UploadImgReq uploadImgReq) throws Exception {
        InputStream inputStream = null;
        try {
            URL url = new URL(uploadImgReq.getImgUrl());
            URLConnection conn = url.openConnection();
            inputStream = conn.getInputStream();
            if (Strings.isEmpty(uploadImgReq.getImgType())) {
                closeInput(inputStream);
                return new FilepathRes(PARAM_ERROR);
            }
            String filetype = formatFileType(uploadImgReq.getImgType());
            log.info(LogFormat.formatMsg("FileUploadServiceImpl.imgStrUrlStream start", filetype, uploadImgReq.getImgType()));

            FilepathRes res = new FilepathRes();
            Map<String, String> pathMap = fileupImgStream(filetype, uploadImgReq.getImgPrePath(), inputStream);
            if (pathMap == null) {
                return new FilepathRes(SYSTEM_ERROR);
            }

            res.setFilePath(pathMap.get("imagePath"));
            res.setThumbPath(pathMap.get("thumbPath"));
            return res;
        } catch (Exception e) {
            log.error(LogFormat.formatMsg("fileuploadStream", "", "exception"), e);
            return new FilepathRes(SYSTEM_ERROR);
        } finally {
            closeInput(inputStream);
        }
    }

    private String formatFileType(String fileType) {

        if (fileType.length() > 0 && fileType.charAt(0) != '.') {

            return "." + fileType;
        }

        return fileType;

    }

    private void closeInput(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {

            }
        }
    }

    public Map<String, String> fileupImgStream(String filetype, String imgPrepath, InputStream in) {

        Map<String, String> map = new HashMap<>();

        String imgFilePath = this.getImgFilePath(imgPrepath, filetype);
        if (imgFilePath == null) {
            log.info(LogFormat.formatMsg("ConfigUtil.fileuploadStream", "imgFilePath is null" + ",filetype=" + filetype, ""));
            return null;
        }
        try {

            log.info(LogFormat.formatMsg("ConfigUtil.fileuploadStream", "file save path=", this.filePath + imgFilePath));

            String path = this.filePath + imgFilePath;
            OutputStream out = new FileOutputStream(path);
            Files.forIO().copy(in, out, Files.AutoClose.INPUT_OUTPUT);
            //图片压缩
            ImageScaleUtil.compress(path, 0.2f, null);

            //如果是图片 则生成缩略图
            if (imgFilePath.contains("img")) {
                //图片缩放
                String thumbPath = ImageScaleUtil.scale(path, 0.2f, "_thumb");

                if (Strings.isNotEmpty(thumbPath)) {
                    thumbPath = thumbPath.replace(this.filePath, "");
                    log.info(LogFormat.formatMsg("ConfigUtil.fileuploadStream", "thumbPath=" + thumbPath, ""));
                    map.put("thumbPath", thumbPath);
                }
            }
            //如果是音频 则生成前30s的试听地址
            if (imgFilePath.contains("audio")) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        map.put("imagePath", imgFilePath);

        return map;
    }

    private String getImgFilePath(String imgPrepath, String filetype) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        String day = sdf.format(new Date());

        StringBuffer sb = new StringBuffer();

        sb.append(imgPrepath).append(day).append(File.separator);

        File filepath = new File(this.filePath + sb.toString());
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        String imgName = ToolUtil.getStringID();
        sb.append(imgName).append(filetype);
        return sb.toString();

    }

}
