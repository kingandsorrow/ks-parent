package top.ks.redis;

/**
 * @param :
 * @author : brj
 * @Method :
 * @Description :商品key
 * @return :
 * @CreateDate : 2019/3/3 21:55
 */
public class CommodityKey extends BasePrefix {

    private CommodityKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static CommodityKey commodityList = new CommodityKey(60, "gl");
    public static CommodityKey commodityDetail = new CommodityKey(60, "cd");

    public static CommodityKey commodityStock = new CommodityKey(0, "cs");

}
