package top.ks.yonyou.db.test;

import java.io.Serializable;

public class CommonVO implements Serializable {
    private String code;

    private String tenantid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }
}
