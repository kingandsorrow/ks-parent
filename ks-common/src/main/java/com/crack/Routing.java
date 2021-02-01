package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * <b>类名称:</b>Routing$<br/>
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
public class Routing implements Serializable {
    @JSONField(name = "RouteType")
    private String routeType;
    @JSONField(name = "RouteValue")
    private String routeValue;

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getRouteValue() {
        return routeValue;
    }

    public void setRouteValue(String routeValue) {
        this.routeValue = routeValue;
    }
}
