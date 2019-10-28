package top.ks.order.api.req;

import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>OrderListReq$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/20<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/20
 */
public class OrderListReq extends RequestEntity {

    private int curNavIndex;

    private int pageNum;

    private int pageSize;

    public int getCurNavIndex() {
        return curNavIndex;
    }

    public void setCurNavIndex(int curNavIndex) {
        this.curNavIndex = curNavIndex;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
