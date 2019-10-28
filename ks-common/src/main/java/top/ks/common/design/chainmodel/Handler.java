package top.ks.common.design.chainmodel;

/**
 * 责任链模式：当某人提出聚餐费用申请的请求后，
 * 该请求会在 项目经理—〉部门经理—〉总经理 这样一条领导处理链上进行传递
 */
public abstract class Handler {

    protected Handler successor = null;


    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 处理聚餐费用的申请
     *
     * @param user 申请人
     * @param fee  申请的钱数
     * @return 成功或失败的具体通知
     */
    public abstract String handleFeeRequest(String user, double fee);

}
