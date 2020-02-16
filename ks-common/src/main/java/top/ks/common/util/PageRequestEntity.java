package top.ks.common.util;


public class PageRequestEntity extends BaseEntity {
    //默认从1开始
    protected int pageNo;
    
    protected int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
