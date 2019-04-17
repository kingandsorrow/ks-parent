package top.ks.cy.web.database.model;

import java.util.Date;

public class UserJoin {
    private String joinId;

    private String phoneNum;

    private String qqNum;

    private String iptvNum;

    private String desc;

    private Date createTime;

    private Date updateTime;

    public String getJoinId() {
        return joinId;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId == null ? null : joinId.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum == null ? null : qqNum.trim();
    }

    public String getIptvNum() {
        return iptvNum;
    }

    public void setIptvNum(String iptvNum) {
        this.iptvNum = iptvNum == null ? null : iptvNum.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}