package top.ks.common.design.strategymode;

public interface MemberStrategy {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :根据原价计算价格
     * @author : birjc
     * @CreateDate : 2019-08-10 10:30
     */
    public double calcPrice(double booksPrice);
}
