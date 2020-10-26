package top.ks.h5.web.model;

import java.util.Date;

public class SpidersUrl {
    private Long id;

    private String url;

    private Byte urlStatus;

    private Byte imgStatus;

    private String sourceUrl;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getUrlStatus() {
        return urlStatus;
    }

    public void setUrlStatus(Byte urlStatus) {
        this.urlStatus = urlStatus;
    }

    public Byte getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Byte imgStatus) {
        this.imgStatus = imgStatus;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}