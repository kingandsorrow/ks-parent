package top.ks.common.enums;

/**
 * <b>类名称:</b>ResultStatus$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/2/13<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/2/13
 */
public enum ResultStatus {
    SUCCESS("0", "成功"),
    FAILD("-1", "失败"),
    EXCEPTION("-1", "系统异常"),
    PARAM_ERROR("10000", "参数错误"),
    SYSTEM_ERROR("10001", "系统错误"),
    FILE_NOT_EXIST("10002", "文件不存在"),
    FILE_NOT_DOWNLOAD("10003", "文件没有下载"),
    SYSTEM_BUSY("10004", "系统繁忙"),
    DATA_NOT_EXSIT("10005", "系统繁忙"),


    /**
     * 注册登录
     */
    RESIGETR_SUCCESS("20000", "注册成功!"),
    RESIGETER_FAIL("200001", "注册失败!"),
    CODE_FAIL("200002", "验证码不一致!"),
    RESIGETER_NICKNAMEEXIST("200003", "用户名已经存在!"),
    LOGIN_FAIL("200004", "登录失败!"),
    PHONE_IS_WRONG("200005", "手机号错误"),
    CODE_IS_WRONG("200006", "验证码错误"),
    TOKEN_EXPIRE("200007", "token过期"),
    LOGIN_EXPIRE("200008", "登录过期"),


    /**
     * check
     */
    BIND_ERROR("30001", "参数校验异常：%s"),
    ACCESS_LIMIT_REACHED("30002", "请求非法!"),
    REQUEST_ILLEGAL("30004", "访问太频繁!"),
    SESSION_ERROR("30005", "Session不存在或者已经失效!"),
    PASSWORD_EMPTY("30006", "登录密码不能为空!"),
    MOBILE_EMPTY("30007", "手机号不能为空!"),
    MOBILE_ERROR("30008", "手机号格式错误!"),
    MOBILE_NOT_EXIST("30009", "手机号不存在!"),
    PASSWORD_ERROR("30010", "密码错误!"),
    USER_NOT_EXIST("30011", "用户不存在！"),
    USER_NOT_LOGIN("30012", "用户未登陆"),
    JOIN_USER_TOO_MUCH("30013", "当前参与人数过多,稍后再试"),


    /**
     * 订单模块
     */
    ORDER_NOT_EXIST("60001", "订单不存在"),

    /**
     * 秒杀模块
     */
    MIAO_SHA_OVER("40001", "商品已经秒杀完毕"),
    REPEATE_MIAOSHA("40002", "不能重复秒杀"),
    MIAOSHA_FAIL("40003", "秒杀失败"),
    MIAOSHA_NOT_EXIST("40004", "秒杀商品不存在");

    /**
     * 商品模块
     */
    private String code;
    private String message;

    private ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public String getOutputName() {
        return this.name();
    }

    public String toString() {
        return this.getName();
    }

    private ResultStatus(Object... args) {
        this.message = String.format(this.message, args);
    }
}
