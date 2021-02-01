package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

public class QRYCONTROLLER_DEMO_REQ {
    @JSONField(name = "APPKEY")
    private String appkey;
    @JSONField(name = "APPTX")
    private String apptx;
    @JSONField(name = "MSG")
    private MSG msg;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getApptx() {
        return apptx;
    }

    public void setApptx(String apptx) {
        this.apptx = apptx;
    }

    public MSG getMsg() {
        return msg;
    }

    public void setMsg(MSG msg) {
        this.msg = msg;
    }
}

