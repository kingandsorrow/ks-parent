package top.ks.sso.api.bean;

import top.ks.common.util.BaseEntity;

import java.util.Date;

/**
 * <b>类名称:</b>KsUserBean$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/16<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/16
 */
public class KsUserBean extends BaseEntity {
    private String userId;

    private String phone;

    private Byte gender;

    private String nickName;

    private String headImg;

    private String provinceId;

    private String cityId;

    private Byte registFrom;

    private Byte frezzType;

    private Byte isRobot;

    private String otherInfo;

    private String projectId;

    private String createTime;

    private String updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Byte getRegistFrom() {
        return registFrom;
    }

    public void setRegistFrom(Byte registFrom) {
        this.registFrom = registFrom;
    }

    public Byte getFrezzType() {
        return frezzType;
    }

    public void setFrezzType(Byte frezzType) {
        this.frezzType = frezzType;
    }

    public Byte getIsRobot() {
        return isRobot;
    }

    public void setIsRobot(Byte isRobot) {
        this.isRobot = isRobot;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
