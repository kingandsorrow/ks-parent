/**
 * Created by Administrator on 2017-08-25.
 */
/**
 * app设置
 * @type {{version: string, include: [*]}}
 */
const APP_VERSION = "4.2.4";
const pathName = window.location.pathname;
const PORJECT_NAME = pathName.substring(0, pathName.substr(1).indexOf('/') + 1) + "/";
var appSetting = {
    version: APP_VERSION,
    include: [
        // meta
        '<meta name="renderer" content="webkit">',
        '<meta content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" name="viewport">',
        '<meta content="yes" name="apple-mobile-web-app-capable">',
        '<meta content="black" name="apple-mobile-web-app-status-bar-style">',
        '<meta name="format-detection" content="telephone=no" />',
        '<meta http-equiv="cache-control" content="no-cache">',
        '<base href="./">',
        //script
        '<script src="https://pv.sohu.com/cityjson?ie=utf-8&qq-pf-to=pcqq.c2c"></script>',
        '<script src="style/js/constant.js?v=#version#"></script>',
        '<script src="style/js/jquery.min.js?v=#version#"></script>',
        '<script src="style/js/layer_mobile/layer.js?v=#version#"></script>',
        '<script src="style/js/vue.min.js?v=#version#"></script>',
        '<script src="style/js/tools.js?v=#version#"></script>',
        '<script src="style/js/rem.js??v=#version#"></script>',
        '<script src="style/js/layer_mobile/layer.js?v=#version#" charset="utf-8"></script>',
        '<link rel="stylesheet" href="style/js/layer_mobile/layer.css?v=#version#" media="all">',
        '<script src="style/js/tools.js?v=#version#"></script>',
        '<script src="style/js/weixinHide.js?v=#version#"></script>',
        '<script src="style/js/json2.js?v=#version#"></script>',
        '<script src="style/js/common.js?v=#version#"></script>',
        '<script src="style/js/tripledes.js?v=#version#"></script>',
        '<script src="style/js/mode-ecb.js?v=#version#"></script>',
    ]
};
appSetting.include.forEach(function (val) {
    val = val.replace("#version#", appSetting.version);
    document.write(val);
});
var isWxMini = window.__wxjs_environment === 'miniprogram';
if (isWxMini) {
    document.write('<script src="thirdParty/weixin/jweixin.js?v=' + appSetting.version + '"></script>');
} else {
    document.write('<script src="style/js/weixin.js?v=' + appSetting.version + '"></script>');
}
