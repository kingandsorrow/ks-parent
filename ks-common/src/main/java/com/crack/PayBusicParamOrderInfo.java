package com.crack;

/**
 * Created by wxx on 2017/5/13 0013.
 * 支付请求实体中的订单信息实体bean
 */
public class PayBusicParamOrderInfo {
    private String orderId;
    private String orderDesc;
    private int orderPrice;

    private String busiKeyType;
    private String busiKey;
    private String busiOccurrTime;//业务发生时间

    public String getBusiOccurrTime() {
        return busiOccurrTime;
    }

    public void setBusiOccurrTime(String busiOccurrTime) {
        this.busiOccurrTime = busiOccurrTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getBusiKeyType() {
        return busiKeyType;
    }

    public void setBusiKeyType(String busiKeyType) {
        this.busiKeyType = busiKeyType;
    }

    public String getBusiKey() {
        return busiKey;
    }

    public void setBusiKey(String busiKey) {
        this.busiKey = busiKey;
    }
}
