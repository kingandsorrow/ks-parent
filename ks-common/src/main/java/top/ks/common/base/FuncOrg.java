package top.ks.common.base;

import java.io.Serializable;

public class FuncOrg implements Serializable {

    private String orgId;

    private String orgName;

    private String orgCode;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public FuncOrg(String orgId, String orgName, String orgCode) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgCode = orgCode;
    }

    public FuncOrg() {
    }


    public static void main(String[] args) {
        String a = "straadas";
        if (a.length() > 6) {
            System.out.println(a.substring(0, 6));
        }
        System.out.println(a);
    }
}
