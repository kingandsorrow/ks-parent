package top.ks.order.database.model;

public class SkOrder {
    private String skOrderId;

    private String userId;

    private String orderId;

    private String commodityId;

    public String getSkOrderId() {
        return skOrderId;
    }

    public void setSkOrderId(String skOrderId) {
        this.skOrderId = skOrderId == null ? null : skOrderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }
}