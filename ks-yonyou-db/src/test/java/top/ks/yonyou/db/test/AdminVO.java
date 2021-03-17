package top.ks.yonyou.db.test;

public class AdminVO {

    private String id;

    private String parentid;

    private String parentorgid;

    private Integer is_biz_unit;

    private Integer orgtype;

    private String innercode;

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

    public String getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(String parentorgid) {
        this.parentorgid = parentorgid;
    }

    public Integer getIs_biz_unit() {
        return is_biz_unit;
    }

    public void setIs_biz_unit(Integer is_biz_unit) {
        this.is_biz_unit = is_biz_unit;
    }

    public Integer getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(Integer orgtype) {
        this.orgtype = orgtype;
    }

    public String getInnercode() {
        return innercode;
    }

    public void setInnercode(String innercode) {
        this.innercode = innercode;
    }
}
