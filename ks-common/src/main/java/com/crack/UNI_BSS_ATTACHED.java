package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class UNI_BSS_ATTACHED implements Serializable {
    @JSONField(
            name = "MEDIA_INFO"
    )
    private String media_info;

    public UNI_BSS_ATTACHED() {
    }

    public String getMedia_info() {
        return this.media_info;
    }

    public void setMedia_info(String media_info) {
        this.media_info = media_info;
    }
}

