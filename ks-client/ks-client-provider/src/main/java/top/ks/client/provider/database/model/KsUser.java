package top.ks.sso.provider.database.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("KsUser")
public class KsUser {
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

    private Date createTime;

    private Date updateTime;

    private String authsId;

    private Byte identityType;

    private String identifier;

    private String credential;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
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
        this.otherInfo = otherInfo == null ? null : otherInfo.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
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

    public String getAuthsId() {
        return authsId;
    }

    public void setAuthsId(String authsId) {
        this.authsId = authsId;
    }

    public Byte getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Byte identityType) {
        this.identityType = identityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}