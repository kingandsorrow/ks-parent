package top.ks.oss.consumer.bean;

import top.ks.common.util.BaseEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <b>类名称:</b>OperatorDeatil$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/4<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright KS 2018/10/4
 */
public class OperatorDeatilBean extends BaseEntity {
    private String operatorId;

    private String loginName;

    private String passwd;

    private String phone;

    private String email;

    private String realName;

    private String department;

    private String career;

    private Byte status;

    private String projectId;

    private String description;

    private Date createTime;

    private Date updateTime;

    private List<KsRoleBean> ksRoleList;

    private List<KsFunctionBean> ksFunctionList;

    private Set<String> permissions;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<KsRoleBean> getKsRoleList() {
        return ksRoleList;
    }

    public void setKsRoleList(List<KsRoleBean> ksRoleList) {
        this.ksRoleList = ksRoleList;
    }

    public List<KsFunctionBean> getKsFunctionList() {
        return ksFunctionList;
    }

    public void setKsFunctionList(List<KsFunctionBean> ksFunctionList) {
        this.ksFunctionList = ksFunctionList;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
