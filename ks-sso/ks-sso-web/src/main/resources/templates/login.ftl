<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>登录页面</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <#--<link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/iCheck/square/blue.css">-->
    <link rel="stylesheet" href="${request.contextPath}/static/css/common.css?201904151719">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/login.css?201914151719"/>

</head>
<body class="hold-transition login-page">
<div class="box">
    <div class="wrap">

    </div>
    <form id="form" name="form" action="${request.contextPath}/doLogin" method="post">
        <input type="hidden" name="loginWay" value="0"/>
        <input type="hidden" name="redirect_url" value="${redirect_url!''}"/>
        <div class="content">
            <div class="tuxiang">
                <img class="tuxiang-img" src="${request.contextPath}/static/images/personalCenter/ge_dl_tx@2x.png"/>
            </div>
            <ul class="ul">
                <li class="li">
                    <input placeholder="点此输入手机号" maxlength="11" class="inphone" type="tel" name="loginName"
                           id="inputPhone"
                           value="17663748077"/>
                </li>
                <li class="li">
                    <input placeholder="请输入验证码" class="incode" type="tel" name="code" id="valideCode" value="123456"/>
                    <div class="getCode">
                        获取验证码
                    </div>
                </li>
            </ul>
            <div class=" tishi">
                登录即同意<span class=userRule">《用户服务条款》</span>
            </div>

            <div class="goLogin">
                <#if errorMsg?exists>
                    <p style="color: red;text-align: center; margin-top: 1rem;font-size: 1.3rem;">${errorMsg}</p>
                </#if>
                <input class="login" type="submit" value="登录">
            </div>
        </div>
    </form>
</div>
<#--<div class="login-box">
    <div class="login-logo">
        <a><b>KS</b>SSO</a>
    </div>
    <form action="${request.contextPath}/doLogin">
        <div class="login-box-body">
            <p class="login-box-msg">统一认证中心</p>
            <div class="form-group has-feedback">
                <input type="text" name="loginName" class="form-control" placeholder="Please input username."
                       value="17663748077" maxlength="50">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="code" class="form-control" placeholder="Please input password."
                       value="1234" maxlength="50">
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
                </div><!-- /.col &ndash;&gt;
                <div class="col-xs-4">
                    <input type="hidden" name="loginWay" value="0"/>
                    <input type="hidden" name="redirect_url" value="${redirect_url!''}"/>
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>
                </div>
            </div>
        </div>
    </form>
</div>-->

</body>
<@netCommon.commonScript />
<script src="${request.contextPath}/static/adminlte/plugins/iCheck/icheck.min.js"></script>
<script src="${request.contextPath}/static/js/login.1.js?d=7"></script>
</html>
