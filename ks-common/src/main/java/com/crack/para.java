package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

public class para {
    @JSONField(
            name = "PARA_ID"
    )
    private String para_id;
    @JSONField(
            name = "PARA_VALUE"
    )
    private String para_value;

    public para() {
    }

    public String getPara_id() {
        return this.para_id;
    }

    public void setPara_id(String para_id) {
        this.para_id = para_id;
    }

    public String getPara_value() {
        return this.para_value;
    }

    public void setPara_value(String para_value) {
        this.para_value = para_value;
    }
}

