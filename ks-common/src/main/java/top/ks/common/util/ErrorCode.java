package top.ks.common.util;


import top.ks.common.enums.ResultStatus;

public class ErrorCode {

    private String code;

    private String msg;

    public ErrorCode() {
    }

    public ErrorCode(String errorCode) {

        this.code = errorCode;
    }

    public ErrorCode(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ErrorCode(String code, String format, Object... args) {
        this.code = code;
        this.msg = format;
        if (msg != null && format != null) {
            this.msg = String.format(format, args);
        }
    }

    public ErrorCode(ResultStatus resultStatus) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMessage();
    }


    @Override
    public String toString() {
        return "ErrorCode [code=" + code + ", msg=" + msg + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
