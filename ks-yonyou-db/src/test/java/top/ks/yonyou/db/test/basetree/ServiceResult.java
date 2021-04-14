package top.ks.yonyou.db.test.basetree;

public class ServiceResult<K> {

    private K data;

    private boolean succeed;

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
