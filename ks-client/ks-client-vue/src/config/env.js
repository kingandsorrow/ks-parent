/**
 * 配置编译环境和线上环境之间的切换
 * 
 * baseUrl: 域名地址
 * routerMode: 路由模式
 * imgBaseUrl: 图片所在域名地址
 * 
 */

let baseUrl = 'localhost:8090';
let routerMode = 'hash';
let imgBaseUrl = 'https://yun.gs-troy.com/cy-index-h5/';


if (process.env.NODE_ENV == 'development') {
    imgBaseUrl = 'https://yun.gs-troy.com/cy-index-h5/';

}else if(process.env.NODE_ENV == 'production'){
	baseUrl = '//elm.cangdu.org';
    imgBaseUrl = '//elm.cangdu.org/img/';
}

export {
	baseUrl,
	routerMode,
	imgBaseUrl,
}
