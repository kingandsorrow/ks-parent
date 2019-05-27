package top.ks.client.api.resp;


import top.ks.common.util.ResponseEntity;

public class FilepathRes extends ResponseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -7717622502690236467L;
    private String filePath;
    private String thumbPath;

    public FilepathRes() {
        super(SUCCESS);
    }


    public FilepathRes(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public FilepathRes(String errCode) {
        super(errCode);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }
}
