package top.ks.yonyou.db.test.event;

import java.io.Serializable;

public class EventObject implements Serializable {

    private String eventType;

    private Object invocationInfo;

    private String sendTime;

    private String sourceID;

    private String userObject;

    private String uuid;

    private String tenantCode;


    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Object getInvocationInfo() {
        return invocationInfo;
    }

    public void setInvocationInfo(Object invocationInfo) {
        this.invocationInfo = invocationInfo;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getUserObject() {
        return userObject;
    }

    public void setUserObject(String userObject) {
        this.userObject = userObject;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }
}
