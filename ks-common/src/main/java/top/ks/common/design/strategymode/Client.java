package top.ks.common.design.strategymode;

/**
 * <b>类名称:</b>Client<br/>
 * <b>类注释:</b>精简策略模式<br/>
 * <b>类描述:</b>
 * 假设现在要设计一个贩卖各类书籍的电子商务网站的购物车系统。
 * 一个最简单的情况就是把所有货品的单价乘上数量，但是实际情况肯定比这要复杂。
 * 比如，本网站可能对所有的高级会员提供每本20%的促销折扣；
 * 对中级会员提供每本10%的促销折扣；对初级会员没有折扣。
 * <br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2019-08-10
 */
public class Client {
    public static void main(String[] args) {
        //选择并创建需要使用的策略对象
        MemberStrategy strategy = new AdvancedMemberStrategy();
        //创建环境
        Price price = new Price(strategy);
        //计算价格
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }
}
