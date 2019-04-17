<#macro commonStyle>

<#-- favicon -->
    <link rel="icon" href="favicon.ico"/>

    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width,initial-scale=0.5,minimum-scale=0.5,maximum-scale=0.5,user-scalable=no"
          name="viewport">
    <meta name="format-detection" content="telephone=no"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta name="format-detection" content="telephone=no"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</#macro>

<#macro commonScript>

    <!-- jQuery 2.1.4 -->
    <script src="${request.contextPath}/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
<#--<script src="${request.contextPath}/static/adminlte/bootstrap/js/bootstrap.min.js"></script>-->
    <!-- FastClick -->
    <script src="${request.contextPath}/static/adminlte/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
<#--<script src="${request.contextPath}/static/adminlte/dist/js/app.min.js"></script>-->
<#-- jquery.slimscroll -->
<#--<script src="${request.contextPath}/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>-->

<#--<script src="${request.contextPath}/static/js/common.1.js"></script>-->
    <script src="${request.contextPath}/static/js/rem.js?a=1"></script>

</#macro>

<#macro commonHeader>
    <header class="main-header">
        <a href="${request.contextPath}/" class="logo">
            <span class="logo-mini"><b>XXL</b></span>
            <span class="logo-lg"><b>XXL SSO</b></span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"><span
                        class="sr-only">切换导航</span></a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="${request.contextPath}/logout">
                            <span class="hidden-xs">注销【${xxlUser.username}】</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
</#macro>

<#macro commonLeft pageName >
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">导航</li>
                <li class="nav-click <#if pageName == "help">active</#if>"><a href="${request.contextPath}/"><i
                                class="fa fa-circle-o text-gray"></i><span>使用教程</span></a></li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
</#macro>

<#macro commonFooter >
    <footer class="main-footer">
        Powered by <b>XXL-SSO</b> 1.1.1-SNAPSHOT
        <div class="pull-right hidden-xs">
            <strong>Copyright &copy; 2018-${.now?string('yyyy')} &nbsp;
                <a href="http://www.xuxueli.com/" target="_blank">xuxueli</a>
                &nbsp;
                <a href="https://github.com/xuxueli/xxl-sso" target="_blank">github</a>
            </strong><!-- All rights reserved. -->
        </div>
    </footer>
</#macro>
