package top.ks.yonyou.db.test;

import java.io.Serializable;

public class Columns implements Serializable {

    private String type_code;

    private String tenantid;

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

}
