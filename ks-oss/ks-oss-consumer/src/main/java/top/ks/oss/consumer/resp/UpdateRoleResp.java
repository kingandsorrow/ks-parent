package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

public class UpdateRoleResp extends ResponseEntity {
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

    public UpdateRoleResp() {
    }

    public UpdateRoleResp(String errCode) {
        super(errCode);
    }

    public UpdateRoleResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public UpdateRoleResp(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
