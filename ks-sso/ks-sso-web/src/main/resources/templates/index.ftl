<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>首页</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <#--<link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/iCheck/square/blue.css">-->
    <link rel="stylesheet" href="${request.contextPath}/static/css/common.css?201904151719">

</head>
<body class="hold-transition login-page">
<div class="box">
    <div class="wrap">
        首页
    </div>
</div>
</body>
<@netCommon.commonScript />
<script src="${request.contextPath}/static/adminlte/plugins/iCheck/icheck.min.js"></script>
</html>
