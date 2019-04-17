package top.ks.commodity.database.model;

public class SkRecord {
    private String skId;

    private String commodityId;

    private String skOrderId;

    public String getSkId() {
        return skId;
    }

    public void setSkId(String skId) {
        this.skId = skId == null ? null : skId.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getSkOrderId() {
        return skOrderId;
    }

    public void setSkOrderId(String skOrderId) {
        this.skOrderId = skOrderId == null ? null : skOrderId.trim();
    }
}