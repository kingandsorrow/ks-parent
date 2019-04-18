package top.ks.oss.api.bean;

import top.ks.common.util.BaseEntity;

/**
 * <b>类名称:</b>MetaBean$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/31<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/10/31
 */
public class MetaBean extends BaseEntity {

    private String[] role;


    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }
}
