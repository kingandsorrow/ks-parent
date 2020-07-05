package top.ks.oss.consumer.req;

import top.ks.common.util.RequestEntity;

public class AddMenuReq extends RequestEntity {

    private String functionId;

    private String title;

    private String description;

    private String url;

    private String authorize;

    private String parentId;

    private Byte orderNum;

    private String icon;

    private Integer type;

    private String remark;

    private String projectId;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Byte getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Byte orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "AddMenuReq{" +
                "functionId='" + functionId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", authorize='" + authorize + '\'' +
                ", parentId='" + parentId + '\'' +
                ", orderNum=" + orderNum +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
