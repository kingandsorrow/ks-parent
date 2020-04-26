package top.ks.file.consumer;


import top.ks.file.consumer.req.FileUploadReq;
import top.ks.file.consumer.resp.FileUploadRes;

import java.io.InputStream;

public interface FileUploadServiceI {

    FileUploadRes fileUploadStream(FileUploadReq fileUploadReq, InputStream inputStream) throws Exception;

}
