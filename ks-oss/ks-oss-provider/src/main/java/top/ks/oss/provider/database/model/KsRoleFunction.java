package top.ks.oss.provider.database.model;

public class KsRoleFunction {
    private String roleFunctionId;

    private String roleId;

    private String functionId;

    private String projectId;

    public String getRoleFunctionId() {
        return roleFunctionId;
    }

    public void setRoleFunctionId(String roleFunctionId) {
        this.roleFunctionId = roleFunctionId == null ? null : roleFunctionId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }
}