package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * <b>类名称:</b>RequestInfo$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/6<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright 西安创意 2018/8/6
 */
public class RequestInfo implements Serializable {
    @JSONField(name = "serialNo")
    private String serialNo;
    @JSONField(name = "shoppingId")
    private String shoppingId;
    @JSONField(name = "shoppingName")
    private String shoppingName;
    @JSONField(name = "flowNum")
    private String flowNum;
    @JSONField(name = "validNum")
    private String validNum;
    @JSONField(name = "validUnit")
    private String validUnit;
    @JSONField(name = "shoppingType")
    private String shoppingType;
    @JSONField(name = "desc")
    private String desc;
    @JSONField(name = "cardName")
    private String cardName;
    @JSONField(name = "price")
    private String price;
    @JSONField(name = "Torderid")
    private String torderid;
    @JSONField(name = "channel")
    private String channel;
    @JSONField(name = "payMode")
    private String payMode;
    @JSONField(name = "tradeChannelId")
    private String tradeChannelId;
    @JSONField(name = "tradeStaffId")
    private String tradeStaffId;
    @JSONField(name = "flowPackagePrice")
    private String flowPackagePrice;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(String shoppingId) {
        this.shoppingId = shoppingId;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public String getValidNum() {
        return validNum;
    }

    public void setValidNum(String validNum) {
        this.validNum = validNum;
    }

    public String getValidUnit() {
        return validUnit;
    }

    public void setValidUnit(String validUnit) {
        this.validUnit = validUnit;
    }

    public String getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(String shoppingType) {
        this.shoppingType = shoppingType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getTradeChannelId() {
        return tradeChannelId;
    }

    public void setTradeChannelId(String tradeChannelId) {
        this.tradeChannelId = tradeChannelId;
    }

    public String getTradeStaffId() {
        return tradeStaffId;
    }

    public void setTradeStaffId(String tradeStaffId) {
        this.tradeStaffId = tradeStaffId;
    }

    public String getFlowPackagePrice() {
        return flowPackagePrice;
    }

    public void setFlowPackagePrice(String flowPackagePrice) {
        this.flowPackagePrice = flowPackagePrice;
    }

    public String getTorderid() {
        return torderid;
    }

    public void setTorderid(String torderid) {
        this.torderid = torderid;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "serialNo='" + serialNo + '\'' +
                ", shoppingId='" + shoppingId + '\'' +
                ", shoppingName='" + shoppingName + '\'' +
                ", flowNum='" + flowNum + '\'' +
                ", validNum='" + validNum + '\'' +
                ", validUnit='" + validUnit + '\'' +
                ", shoppingType='" + shoppingType + '\'' +
                ", desc='" + desc + '\'' +
                ", cardName='" + cardName + '\'' +
                ", price='" + price + '\'' +
                ", Torderid='" + torderid + '\'' +
                ", channel='" + channel + '\'' +
                ", payMode='" + payMode + '\'' +
                ", tradeChannelId='" + tradeChannelId + '\'' +
                ", tradeStaffId='" + tradeStaffId + '\'' +
                ", flowPackagePrice='" + flowPackagePrice + '\'' +
                '}';
    }
}
