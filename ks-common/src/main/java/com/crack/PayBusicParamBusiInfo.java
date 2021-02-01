package com.crack;

import java.util.List;

/**
 * Created by wxx on 2017/5/13 0013.
 * 支付业务参数中的业务信息实体bean
 */
public class PayBusicParamBusiInfo {

    private String provinceCode;
    private String busiType;
    private String regionId;
    private List<PayBusicParamOrderInfo> orderInfos;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public List<PayBusicParamOrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<PayBusicParamOrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }
}
