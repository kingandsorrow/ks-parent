package top.ks.commodity.database.model;

import java.math.BigDecimal;
import java.util.Date;

public class SkCommodity {
    private String skId;

    private Long commodityId;

    private BigDecimal skPrice;

    private Integer skStockCount;

    private Date startDate;

    private Date endDate;

    public String getSkId() {
        return skId;
    }

    public void setSkId(String skId) {
        this.skId = skId == null ? null : skId.trim();
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public BigDecimal getSkPrice() {
        return skPrice;
    }

    public void setSkPrice(BigDecimal skPrice) {
        this.skPrice = skPrice;
    }

    public Integer getSkStockCount() {
        return skStockCount;
    }

    public void setSkStockCount(Integer skStockCount) {
        this.skStockCount = skStockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}