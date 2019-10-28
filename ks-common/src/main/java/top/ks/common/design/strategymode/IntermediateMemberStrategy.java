package top.ks.common.design.strategymode;
/**
 * <b>类名称:</b>IntermediateMemberStrategy<br/>
 * <b>类注释:</b>中级会员折扣类<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 * @version 1.0.0<br/>
 *
 * Copyright KS 2019-08-10
 */
public class IntermediateMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }
}
