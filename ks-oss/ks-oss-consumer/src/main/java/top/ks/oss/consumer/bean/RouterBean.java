package top.ks.oss.consumer.bean;

import top.ks.common.util.BaseEntity;

import java.util.List;

/**
 * <b>类名称:</b>RouterBean$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/31<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/10/31
 */
public class RouterBean extends BaseEntity {
    private String path;
    private String component;
    private String name;
    private MetaBean meta;
    private String redirect;
    private boolean hidden;
    private List<RouterBean> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<RouterBean> getChildren() {
        return children;
    }

    public void setChildren(List<RouterBean> children) {
        this.children = children;
    }
}
