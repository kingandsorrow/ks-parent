package top.ks.oss.api.req;

import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>RoleAddReq$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/1<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/1
 */
public class RoleAddReq extends RequestEntity {

    private String projectId;

    private String roleId;

    private String roleName;

    private String description;

    private String[] menuIdList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(String[] menuIdList) {
        this.menuIdList = menuIdList;
    }
}
