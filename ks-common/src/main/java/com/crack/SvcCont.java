package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * <b>类名称:</b>SvcCont$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/6<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 * <p>
 * Copyright 西安创意 2018/8/6
 */
public class SvcCont implements Serializable {
    @JSONField(name = "RequestInfo")
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
