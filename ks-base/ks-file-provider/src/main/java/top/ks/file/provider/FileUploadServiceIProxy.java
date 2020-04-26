package top.ks.file.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ks.common.util.Files;
import top.ks.common.util.Strings;
import top.ks.file.consumer.FileUploadServiceI;
import top.ks.file.consumer.req.FileUploadReq;
import top.ks.file.consumer.resp.FileUploadRes;
import top.ks.file.provider.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <b>类名称:</b>FileUploadServiceIProxy<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2020-04-04
 */
@Component
public class FileUploadServiceIProxy implements FileUploadServiceI {
    @Value("${file.path.pre}")
    private String filePathPre;

    @Override
    public FileUploadRes fileUploadStream(FileUploadReq fileUploadReq, InputStream inputStream) throws Exception {
        //1.校验文件类型
        if (Strings.isEmpty(fileUploadReq.getFileType())) {
            FileUtil.closeInput(inputStream);
            return new FileUploadRes();
        }
        //2.校验
        String filetype = FileUtil.preAppendDoc(fileUploadReq.getFileType());
        FileUploadRes res = new FileUploadRes();
        File linuxFilePath = new File(this.filePathPre);
        if (!linuxFilePath.exists()) {
            linuxFilePath.mkdirs();
        }
        String path = this.filePathPre + File.separator + fileUploadReq.getFileName() + filetype;
        OutputStream out = new FileOutputStream(path);
        Files.forIO().copy(inputStream, out, Files.AutoClose.INPUT_OUTPUT);
        return res;
    }


}
