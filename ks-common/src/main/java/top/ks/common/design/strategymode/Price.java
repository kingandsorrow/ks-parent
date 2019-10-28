package top.ks.common.design.strategymode;

public class Price {

    private MemberStrategy memberStrategy;

    public Price(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }


    public double quote(double booksPrice) {
        return this.memberStrategy.calcPrice(booksPrice);
    }
}
