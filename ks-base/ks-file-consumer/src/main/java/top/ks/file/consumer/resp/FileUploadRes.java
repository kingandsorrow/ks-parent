package top.ks.file.consumer.resp;

import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>FileUploadRes<br/>
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
public class FileUploadRes extends ResponseEntity {

    private static final long serialVersionUID = -7717622502690236467L;
    private String filePath;

    public FileUploadRes() {
        super(SUCCESS);
    }


    public FileUploadRes(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public FileUploadRes(String errCode) {
        super(errCode);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
