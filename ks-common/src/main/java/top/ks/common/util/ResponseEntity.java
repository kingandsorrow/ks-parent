package top.ks.common.util;


import top.ks.common.enums.ResultStatus;

public class ResponseEntity extends BaseEntity {

    private static final long serialVersionUID = 7943145699254262674L;

    public final static String SUCCESS = "0";
    // 系统异常
    public final static String SYSTEM_ERROR = "9999";
    // 协议错误
    public final static String NO_TRACSCODE = "9998";
    // 参数错误
    public final static String PARAM_ERROR = "9993";

    protected String errCode;
    protected String errMsg;

    public ResponseEntity() {
    }

    public ResponseEntity(String errCode) {
        this.errCode = errCode;
    }

    public ResponseEntity(String errCode, String errMsg) {

        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ResponseEntity(ResultStatus resultStatus) {

        this.errCode = resultStatus.getCode();
        this.errMsg = resultStatus.getMessage();
    }

    /**
     * 返回对象是否是处理成功对象
     * @return
     */
    public boolean respSuc() {

        return SUCCESS.equals(this.errCode);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


}
