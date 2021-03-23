package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

public class AddRoleResp extends ResponseEntity {

    private String roleId;

    private String roleName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public AddRoleResp(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public AddRoleResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public AddRoleResp(String errCode, String roleId, String roleName) {
        super(errCode);
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public AddRoleResp(String errCode, String errMsg, String roleId, String roleName) {
        super(errCode, errMsg);
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public AddRoleResp(ResultStatus resultStatus, String roleId, String roleName) {
        super(resultStatus);
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public AddRoleResp() {
    }

    public AddRoleResp(String errCode) {
        super(errCode);
    }
}
