package top.ks.client.api.req;


import top.ks.common.util.RequestEntity;

public class UploadImgReq extends RequestEntity {

    private String imgUrl;
    //jpg 还是png
    private String imgType;
    //图片前缀
    private String imgPrePath;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getImgPrePath() {
        return imgPrePath;
    }

    public void setImgPrePath(String imgPrePath) {
        this.imgPrePath = imgPrePath;
    }
}
