package top.ks.commodity.database.model;

import java.math.BigDecimal;

public class Commodity {
    private String commodityId;

    private String commodityName;

    private String commodityTitle;

    private String commodityImg;

    private BigDecimal commodityPrice;

    private Integer commodityStock;

    private String commodityDetail;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle == null ? null : commodityTitle.trim();
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg == null ? null : commodityImg.trim();
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommodityStock() {
        return commodityStock;
    }

    public void setCommodityStock(Integer commodityStock) {
        this.commodityStock = commodityStock;
    }

    public String getCommodityDetail() {
        return commodityDetail;
    }

    public void setCommodityDetail(String commodityDetail) {
        this.commodityDetail = commodityDetail == null ? null : commodityDetail.trim();
    }
}