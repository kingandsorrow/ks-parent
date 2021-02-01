package com.crack;

/**
 * Created by wxx on 2017/5/13 0013.
 * 支付业务参数实体
 */
public class PayBusicParam {

    private String outTradeNo; //外部流水号
    private String tradeType;   //支付方式
    private String openid;  //openId（应用openid、微信openid）  微信支付为公众号的openid
    private int actualAmount;
    private int totalAmount; //即actualAmount
    private String outTradeDesc;
    private String serviceId;
    private String notifyUrl;
    private String returnUrl;
    private String expireTime; //交易关闭时间
    private PayBusicParamBusiInfo busiInfo;

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getOutTradeDesc() {
        return outTradeDesc;
    }

    public void setOutTradeDesc(String outTradeDesc) {
        this.outTradeDesc = outTradeDesc;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public PayBusicParamBusiInfo getBusiInfo() {
        return busiInfo;
    }

    public void setBusiInfo(PayBusicParamBusiInfo busiInfo) {
        this.busiInfo = busiInfo;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
