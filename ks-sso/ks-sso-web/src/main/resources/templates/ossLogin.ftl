<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>统一认证中心</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <link rel="stylesheet" href="${request.contextPath}/static/plugins/iCheck/square/blue.css">

</head>
<body class="hold-transition login-page">

<div class="login-box">
    <div class="login-logo">
        <a><b>XXL</b>SSO</a>
    </div>
    <form action="${request.contextPath}/doLogin">
        <#--登录方式-->
        <input type="hidden" name="loginWay" value="0">
        <input type="hidden" name="projectId" value="0">
        <div class="login-box-body">
            <p class="login-box-msg">统一认证中心</p>
            <div class="form-group has-feedback">
                <input type="text" name="loginName" class="form-control" placeholder="Please input username."
                       value="user" maxlength="50">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="loginPassWord" class="form-control" placeholder="Please input password."
                       value="123456" maxlength="50">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <#if errorMsg?exists>
                <p style="color: red;">${errorMsg}</p>
            </#if>

            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox" name="ifRemember">记住密码
                        </label>
                    </div>
                </div><!-- /.col -->
                <div class="col-xs-4">
                    <input type="hidden" name="redirect_url" value="${redirect_url!''}"/>
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
<@netCommon.commonScript />
<script src="${request.contextPath}/static/plugins/iCheck/icheck.min.js"></script>
<script src="${request.contextPath}/static/js/login.1.js"></script>
</html>