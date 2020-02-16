package top.ks.oss.consumer.req;

import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>MenuListReq$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/30<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/11/30
 */
public class MenuListReq extends RequestEntity {

    private String projectId;
    private String token;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
