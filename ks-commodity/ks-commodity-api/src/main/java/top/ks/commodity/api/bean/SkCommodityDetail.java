package top.ks.commodity.api.bean;

import top.ks.framework.base.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>类名称:</b>SkCommodityDetail$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/20
 */
public class SkCommodityDetail extends BaseEntity {
    private String skId;

    private String commodityId;

    private Double skPrice;

    private Integer skStockCount;

    private String startDate;

    private String endDate;


    public String getSkId() {
        return skId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public Double getSkPrice() {
        return skPrice;
    }

    public void setSkPrice(Double skPrice) {
        this.skPrice = skPrice;
    }

    public Integer getSkStockCount() {
        return skStockCount;
    }

    public void setSkStockCount(Integer skStockCount) {
        this.skStockCount = skStockCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
}
