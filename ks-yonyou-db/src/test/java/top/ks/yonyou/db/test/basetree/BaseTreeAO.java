package top.ks.yonyou.db.test.basetree;

import java.util.ArrayList;
import java.util.List;

public class BaseTreeAO<T> {


    private String id;

    private String parentid;

    //祖先路径：逗号分隔
    private String ancestorpath;
    /**
     * 子类
     */
    private List<T> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public String getAncestorpath() {
        return ancestorpath;
    }

    public void setAncestorpath(String ancestorpath) {
        this.ancestorpath = ancestorpath;
    }
}
