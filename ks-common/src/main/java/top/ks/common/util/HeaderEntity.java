package top.ks.common.util;


public class HeaderEntity extends BaseEntity {

    private static final long serialVersionUID = 7649306993954791715L;
    // 来源：web
    public static final int SOURCE_WEB = 0;
    // 来源：IOS APP
    public static final int SOURCE_APP_IOS = 2;
    // 来源：ANDROID APP
    public static final int SOURCE_APP_ANDROID = 1;
    private String userId;
    private String ip;
    private int source;
    private String[] dataAuthority;

    public String[] getDataAuthority() {
        return dataAuthority;
    }

    public void setDataAuthority(String[] dataAuthority) {
        this.dataAuthority = dataAuthority;
    }

    public HeaderEntity userId(String userId) {

        this.userId = userId;
        return this;
    }

    public HeaderEntity ip(String ip) {

        this.ip = ip;
        return this;
    }

    public HeaderEntity source(int source) {

        this.source = source;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

}
