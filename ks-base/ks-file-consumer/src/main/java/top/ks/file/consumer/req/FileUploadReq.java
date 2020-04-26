package top.ks.file.consumer.req;

import lombok.Getter;
import lombok.Setter;
import top.ks.common.util.RequestEntity;

@Setter
@Getter
public class FileUploadReq extends RequestEntity {

    private String fileUrl;
    //jpg 还是png
    private String fileType;
    //图片前缀
    private String fileName;


}
