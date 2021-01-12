package top.ks.yonyou.db.test;

import java.io.Serializable;

public class CommandVo implements Serializable {

    private String name;

    private String action;

    private String billnumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }
}
