package top.ks.common.util;


public class RequestEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 2138671690435913059L;
    protected HeaderEntity header;

    public RequestEntity() {

        this.header = new HeaderEntity();
    }

    public HeaderEntity getHeader() {
        return header;
    }

    public void setHeader(HeaderEntity header) {
        this.header = header;
    }

}
