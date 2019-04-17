package top.ks.common.constant;

import top.ks.framework.util.StatusCodeManager;

public class ErrorCode {
    private String code;
    private String msg;

    public ErrorCode() {
    }

    public ErrorCode(String errorCode) {
        this.code = errorCode;
        this.msg = StatusCodeManager.getCodeMsg(errorCode);
    }

    public ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorCode(String code, String format, Object... args) {
        this.code = code;
        this.msg = format;
        if (this.msg != null && format != null) {
            this.msg = String.format(format, args);
        }

    }

    public String toString() {
        return "ErrorCode [code=" + this.code + ", msg=" + this.msg + "]";
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
