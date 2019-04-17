package top.ks.oss.provider.database.model;

public class KsOperatorRole {
    private String operatorRoleId;

    private String operatorId;

    private String roleId;

    private String projectId;

    public String getOperatorRoleId() {
        return operatorRoleId;
    }

    public void setOperatorRoleId(String operatorRoleId) {
        this.operatorRoleId = operatorRoleId == null ? null : operatorRoleId.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }
}