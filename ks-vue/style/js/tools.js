/**
 * Created by 王明远 on 2017-08-02.
 * 常用工具类
 * ES6版本
 */
"use strict";

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

var tools = function () {
    //判断对象是否为空
    function pri_isNull(obj) {
        var pri_obj = obj || '';
        if (pri_obj === '') {
            return true;
        } else {
            return false;
        }
    }

    return {
        /**
         * 判断对象是否为空
         * @param obj:对象
         * @returns {boolean}
         */
        isNull: function isNull(obj) {
            return pri_isNull(obj);
        },
        isNotNull: function isNotNull(obj) {
            return !pri_isNull(obj);
        },

        /**
         * 判断对象是不是一个function
         * @param obj
         * @returns {boolean}
         */
        isFunction: function isFunction(obj) {
            return typeof obj === 'function';
        },

        /**
         * 判断对象是不是一个function
         * @param obj
         * @returns {boolean}
         */
        isString: function isString(obj) {
            return typeof obj === 'string';
        },

        /**
         * 判断对象是不是一个function
         * @param obj
         * @returns {boolean}
         */
        isNumber: function isNumber(obj) {
            return typeof obj === 'number';
        },

        /**
         * 打印显示一个对象所有的属性和方法
         * @param msg
         */
        log: function log(msg) {
            console.dir(msg);
        },

        /**
         * 获得当前地址的参数信息
         * @param name:string
         * @returns {info}
         */
        getParam: function getParam(name) {
            var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
            if (tools.isNUll(result) || result.length < 1) {
                return "";
            }
            return result[1];
        },

        /**
         * 去除空字符串
         * @param str 来源字符串
         * @param is_global 是否去除中间的空格
         * @returns {string|XML|void|*}
         */
        trim: function trim(str, is_global) {
            var result = void 0;
            result = str.replace(/(^\s+)|(\s+$)/g, "");
            if (is_global) {
                result = result.replace(/\s/g, "");
            }
            return result;
        },

        /**
         * 多行合并成一行
         * @returns {XML|string|void|*}
         */
        newLine: function newLine(str) {
            var result = void 0;
            result = str.replace(/(\n)+|(\r\n)+/g, "");
            return result;
        },
        encrypt: function encrypt(message, key) {
            if (tools.isNull(key)) {
                key = CONSTANT.PASSWORD;
            }
            var keyHex = CryptoJS.enc.Utf8.parse(key);
            var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            return encrypted.toString();
        },
        decrypt: function decrypt(ciphertext, key) {
            if (tools.isNull(key)) {
                key = CONSTANT.PASSWORD;
            }
            var keyHex = CryptoJS.enc.Utf8.parse(key);
            // direct decrypt ciphertext
            var decrypted = CryptoJS.DES.decrypt({
                ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
            }, keyHex, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            return decrypted.toString(CryptoJS.enc.Utf8);
        },

        /**
         * 判断是不是微信
         * @returns {boolean}
         */
        isWeiXin: function isWeiXin() {
            var ua = window.navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == 'micromessenger') {
                return true;
            } else {
                return false;
            }
        },

        /**
         * 判断是ios还是android
         * @return {*}
         */
        version: function version() {
            if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                //判断iPhone|iPad|iPod|iOS
                //alert(navigator.userAgent);
                return "iOS";
            } else if (/(Android)/i.test(navigator.userAgent)) {
                //判断Android
                return "android"; //alert(navigator.userAgent);
            } else {
                //pc
                return "android";
            }
        },

        /**
         * 将日期字符串转成标准Date日期
         * @returns {Date}
         */
        getDate: function getDate(strDate) {
            strDate = strDate.replace(/-/g, "/");
            return new Date(strDate);
        },

        /**
         * 日期对象转化成毫秒数
         * @param date
         * @returns {number}
         */
        dateToLong: function dateToLong(date) {
            return date.getTime();
        },

        /**
         * 毫秒转换成日期对象
         * @param dateVal (number) 日期的毫秒数
         * @returns {Date}
         */
        longToDate: function longToDate(dateVal) {
            return new Date(dateVal);
        },

        /**
         * 获取对象的唯一标识符
         * @returns {string}
         */
        uniqueId: function uniqueId() {
            var ramdomLength = 11;
            return Number(Math.random().toString().substr(3, ramdomLength) + Date.now()).toString(36);
        },

        /**
         * 随机数时间戳
         * @return {string}
         */
        randomTime: function randomTime() {
            var a = Math.random,
                b = parseInt;
            return Number(new Date()).toString() + b(10 * a()) + b(10 * a()) + b(10 * a());
        },

        /**
         * 计算两数乘积（解决精度丢失）
         * @param arg1  乘数1
         * @param arg2  乘数2
         * @return {number} 积
         */
        accMul: function accMul(arg1, arg2) {
            var m = 0,
                s1 = arg1.toString(),
                s2 = arg2.toString();
            try {
                m += s1.split(".")[1].length;
            } catch (e) {}
            try {
                m += s2.split(".")[1].length;
            } catch (e) {}
            return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
        },

        /**
         * 将字符串作为URI组件进行编码
         * 只对文字进行编码,数字和特殊字符以及字母不编码
         * @param str
         * @returns {string}
         */
        strEncode: function strEncode(str) {
            return encodeURIComponent(str);
        },

        /**
         * 字符串解码
         * @param str
         * @returns {string}
         */
        strDencode: function strDencode(str) {
            return decodeURIComponent(str);
        },

        /**
         * 获取域名主机
         * @param url:域名
         * @returns {string}
         */
        getHost: function getHost(url) {
            var host = "null";
            if (tools.isNUll(url)) {
                url = window.location.href;
            }
            var regex = /^\w+\:\/\/([^\/]*).*/;
            var match = url.match(regex);
            if (tools.isNotNull(match)) {
                host = match[1];
            }
            return host;
        },

        /**
         * 转义html标签
         * 在评论的时候为了防止用户提交带有恶意的脚本，可以先过滤HTML标签，过滤掉双引号，单引号，符号&，符号<，符号
         * @param text:string
         * @returns {string|XML}
         */
        htmlEncode: function htmlEncode(html) {
            var temp = document.createElement("div");
            temp.textContent != null ? temp.textContent = html : temp.innerText = html;
            var output = temp.innerHTML;
            temp = null;
            return output;
            //方法2:
            // return html.replace(/&/g, '&amp').replace(/\"/g, '&quot;').replace(/</g,'&lt;').replace(/>/g, '&gt;');
        },

        /**
         * 反转义html标签
         * @param text:string
         * @returns {string}
         */
        HTMLDecode: function HTMLDecode(text) {
            var temp = document.createElement("div");
            temp.innerHTML = text;
            var output = temp.innerText || temp.textContent;
            temp = null;
            return output;
            //方法2:
            //return text.replace(/&amp;/g, '&').replace(/&quot;/g, '\"').replace(/&lt;/g, '<').replace(/&gt;/g, '>');
        },

        /**
         * 返回浏览器的版本
         * @returns {type,version}
         */
        getExplorerInfo: function getExplorerInfo() {
            var explorer = window.navigator.userAgent.toLowerCase();
            // ie
            if (explorer.indexOf("msie") >= 0) {
                var ver = explorer.match(/msie ([\d.]+)/)[1];
                return {
                    type: "IE",
                    version: ver
                };
            }
            // firefox
            else if (explorer.indexOf("firefox") >= 0) {
                var ver = explorer.match(/firefox\/([\d.]+)/)[1];
                return {
                    type: "Firefox",
                    version: ver
                };
            }
            // Chrome
            else if (explorer.indexOf("chrome") >= 0) {
                var ver = explorer.match(/chrome\/([\d.]+)/)[1];
                return {
                    type: "Chrome",
                    version: ver
                };
            }
            // Opera
            else if (explorer.indexOf("opera") >= 0) {
                var ver = explorer.match(/opera.([\d.]+)/)[1];
                return {
                    type: "Opera",
                    version: ver
                };
            }
            // Safari
            else if (explorer.indexOf("Safari") >= 0) {
                var ver = explorer.match(/version\/([\d.]+)/)[1];
                return {
                    type: "Safari",
                    version: ver
                };
            }
        },

        /**
         * 获得当前路径
         * @return {string}
         */
        getCurrentPageUrl: function getCurrentPageUrl() {
            var currentPageUrl = "";
            if (typeof this.href === "undefined") {
                currentPageUrl = document.location.toString().toLowerCase();
            } else {
                currentPageUrl = this.href.toString().toLowerCase();
            }
            return currentPageUrl;
        },

        /**
         * ip转整型
         * @param ip
         * @return {number}
         * @private
         */
        _ip2int: function _ip2int(ip) {
            var num = 0;
            ip = ip.split(".");
            num = Number(ip[0]) * 256 * 256 * 256 + Number(ip[1]) * 256 * 256 + Number(ip[2]) * 256 + Number(ip[3]);
            num = num >>> 0;
            return num;
        },

        /**
         * 整型转ip地址
         * @param num
         * @return {string|*}
         * @private
         */
        _int2iP: function _int2iP(num) {
            var str;
            var tt = new Array();
            tt[0] = num >>> 24 >>> 0;
            tt[1] = num << 8 >>> 24 >>> 0;
            tt[2] = num << 16 >>> 24;
            tt[3] = num << 24 >>> 24;
            str = String(tt[0]) + "." + String(tt[1]) + "." + String(tt[2]) + "." + String(tt[3]);
            return str;
        },

        /**
         * 实现checkbox全选和全不选
         * @param selectAllBoxId
         * @param childBoxsId
         */
        checkAll: function checkAll(selectAllBoxId, childBoxsId) {
            var selectall = document.getElementById(selectAllBoxId);
            var allbox = document.getElementsByName(childBoxsId);
            if (selectall.checked) {
                for (var i = 0; i < allbox.length; i++) {
                    allbox[i].checked = true;
                }
            } else {
                for (var i = 0; i < allbox.length; i++) {
                    allbox[i].checked = false;
                }
            }
        },

        /**
         * 判断是否移动设备访问
         * @return {boolean}
         */
        isMobileUserAgent: function isMobileUserAgent() {
            return (/iphone|ipod|android.*mobile|windows.*phone|blackberry.*mobile/i.test(window.navigator.userAgent.toLowerCase())
            );
        },

        /**
         * 判断是否苹果移动设备访问
         * @return {boolean}
         */
        isAppleMobileDevice: function isAppleMobileDevice() {
            return (/iphone|ipod|ipad|Macintosh/i.test(navigator.userAgent.toLowerCase())
            );
        },

        /**
         * 判断是否安卓移动设备访问
         * @return {boolean}
         */
        isAndroidMobileDevice: function isAndroidMobileDevice() {
            return (/android/i.test(navigator.userAgent.toLowerCase())
            );
        },

        /**
         * 判断是否为touch屏幕
         * @return {boolean|*}
         */
        isTouchScreen: function isTouchScreen() {
            return 'ontouchstart' in window || window.DocumentTouch && document instanceof DocumentTouch;
        },

        /**
         * 判断是否在安卓上的谷歌浏览器
         * @return {boolean}
         */
        isNewChromeOnAndroid: function isNewChromeOnAndroid() {
            if (this.isAndroidMobileDevice()) {
                var userAgent = navigator.userAgent.toLowerCase();
                if (/chrome/i.test(userAgent)) {
                    var parts = userAgent.split('chrome/');

                    var fullVersionString = parts[1].split(" ")[0];
                    var versionString = fullVersionString.split('.')[0];
                    var version = parseInt(versionString);

                    if (version >= 27) {
                        return true;
                    }
                }
            }
            return false;
        },

        /**
         * 阻止冒泡
         * @param e
         */
        stopBubble: function stopBubble(e) {
            e = e || window.event;
            if (e.stopPropagation) {
                e.stopPropagation(); //W3C阻止冒泡方法
            } else {
                e.cancelBubble = true; //IE阻止冒泡方法
            }
        },

        /**
         * 获得浏览器url中的参数值
         * @param name
         * @return {string|null}
         */
        getUrlParam: function getUrlParam(name) {
            return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)', "ig").exec(location.search) || [, ""])[1].replace(/\+/g, '%20')) || null;
        },

        /**
         * 深度拷贝对象
         * @param obj
         * @return {*}
         */
        cloneObj: function cloneObj(obj) {
            var o = obj.constructor == Object ? new obj.constructor() : new obj.constructor(obj.valueOf());
            for (var key in obj) {
                if (o[key] != obj[key]) {
                    if (_typeof(obj[key]) == 'object') {
                        o[key] = mods.cloneObj(obj[key]);
                    } else {
                        o[key] = obj[key];
                    }
                }
            }
            return o;
        },

        /**
         * 数组去重
         * @param arr
         * @return {Array}
         */
        unique: function unique(arr) {
            var result = [],
                json = {};
            for (var i = 0, len = arr.length; i < len; i++) {
                if (!json[arr[i]]) {
                    json[arr[i]] = 1;
                    result.push(arr[i]); //返回没被删除的元素
                }
            }
            return result;
        },

        /**
         * 判断数组元素是否重复
         * @return {boolean}
         */
        isRepeat: function isRepeat() {
            var hash = {};
            for (var i in arr) {
                if (hash[arr[i]]) return true;
                hash[arr[i]] = true;
            }
            return false;
        },

        /**
         * 生成随机数
         * @param min
         * @param max
         * @return {*}
         */
        randombetween: function randombetween(min, max) {
            return min + Math.random() * (max - min + 1);
        },

        /**
         * 计算字符串的真正长度
         * String有个属性length，但是它不能区分英文字符，
         * 计算中文字符和全角字符。但是在数据存储的时候中文和全角都是用两个字节来存储的，
         * @param str
         * @return {number}
         */
        codeLength: function codeLength(str) {
            var len = 0;
            if (this == null || this.length == 0) return 0;
            var string = str.replace(/(^\s*)|(\s*$)/g, ""); //去掉空格
            for (var i = 0; i < string.length; i++) {
                if (string.charCodeAt(i) > 0 && string.charCodeAt(i) < 128) len++;else len += 2;
            }return len;
        },

        /**
         * 判断电子邮件是否符合规范
         * @param string
         * @return {boolean}
         */
        emailcheck: function emailcheck(string) {
            var re;
            re = new RegExp("^[\\w-_\\.]+@([a-z|0-9|-]+\\.)+[a-z]{2,5}$");
            return re.test(string.toLowerCase());
        },

        /**
         * 根据年和月取当月的最后一天(也就是当月有多少天)
         * @param year
         * @param month
         * @return {number}
         */
        getLastDay: function getLastDay(year, month) {
            //取年
            var new_year = year;
            //取到下一个月的第一天,注意这里传入的month是从1～12
            var new_month = month++;
            //如果当前是12月，则转至下一年
            if (month > 12) {
                new_month -= 12;
                new_year++;
            }
            var new_date = new Date(new_year, new_month, 1);
            return new Date(new_date.getTime() - 1000 * 60 * 60 * 24).getDate();
        },

        /**
         * 创建用于ajax技术的 XMLHttpRequest对象。
         * 用法：
         * try{
         * createXMLHttpRequest();
         * xmlHttp_siteTotal.open("POST", url);
         * xmlHttp_siteTotal.send(xml);
         * }catch(e) {
         * }
         */
        createXMLHttpRequest: function createXMLHttpRequest() {
            var xmlHttp_siteTotal;
            if (window.XMLHttpRequest) {
                xmlHttp_siteTotal = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xmlHttp_siteTotal = new ActiveXObject("Microsoft.XMLHTTP");
            }
        },

        /**
         * 将图片src转成data64
         * @param img
         * @return {string}
         */
        createImgData: function createImgData(img) {
            var image = new Image();
            image.src = img.src || img;
            var tmpCanvas = $("<canvas></canvas>")[0];
            var tmpCtx = tmpCanvas.getContext("2d");
            if (tmpCanvas) {
                tmpCanvas.width = image.width;
                tmpCanvas.height = image.height;
                tmpCtx.drawImage(image, 0, 0);
                return tmpCanvas.toDataURL();
            }
        },
        getLocation: function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (p) {
                    callback(p.coords.latitude, p.coords.longitude);
                }, function (e) {
                    var msg = e.code + "\n" + e.message;
                });
            }
        },

        /**
         * 获取网页参数
         * @param name
         * @return {null}
         */
        getQueryString: function getQueryString(name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        },

        /**
         * 是否还有下页数据
         * @param list
         * @param pageSize
         * @returns {boolean}
         */
        isNextPage: function isNextPage(list, pageSize) {
            var page = 10;
            if (tools.isNotNull(pageSize)) {
                page = pageSize;
            }
            if (tools.isNotNull(list) && list.length == page) {
                return true;
            }
            return false;
        },
        formatDateTime: function formatDateTime(inputTime) {
            var date = new Date(inputTime);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? '0' + m : m;
            var d = date.getDate();
            d = d < 10 ? '0' + d : d;
            var h = date.getHours();
            h = h < 10 ? '0' + h : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? '0' + minute : minute;
            second = second < 10 ? '0' + second : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
        },
    };
}();

/**
 * 日志工具类
 * 
 * @type {{msg}}
 * @param info信息
 *            obj对象
 */
var log = function () {

    return {
        msg: function msg(info, obj) {
            console.warn(info);
            if (tools.isNotNull(obj)) {
                console.log(obj);
            }
        }
    };
}();

var dataTool1 = function () {
    return {
        setCookie: function setCookie(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
            var expires = 'expires=' + d.toUTCString();
            document.cookie = cname + '=' + cvalue + '; ' + expires;
        },
        getCookie: function getCookie(cname) {
            var name = cname + '=';
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') {
                    c = c.substring(1);
                }if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
            }
            return '';
        },
        deleteCookie: function deleteCookie(name) {
            var date = new Date();
            date.setTime(date.getTime() - 10000);
            document.cookie = name + "=; expire=" + date.toGMTString();
        }
    };
}();

var httpTool = function () {

    var _loading = null;

    var _option = {};

    function _post(url, param, _success, _error, _complete, option) {
        $.ajax({
            type: "post",
            url: url,
            data: param,
            timeout: 50000,
            //跨域请求设置
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            beforeSend: function beforeSend() {
                if (_option.loading == true) {
                    _loading = layerTool.loading();
                }
            },
            success: function success(data) {
                // console.warn("------- request end 【" + param.t + "】 -------");
                // console.log("transcode:" + param.t);
                // console.log("param:", param);
                // console.log("resp:", data);
                data = tools.newLine(data);
                data = JSON.parse(tools.decrypt(data, CONSTANT.PASSWORD));
                console.log(data);
                var status = data.errCode;
                if (tools.isNotNull(status) && status != "0000" && status != "9802") {
                    var errMsg = data.errMsg;
                    if (tools.isNotNull(errMsg)) {
                        layerTool.msg(errMsg);
                    } else {
                        layerTool.msg("未知错误,错误码:" + status);
                    }
                    if (tools.isFunction(_error)) {
                        _error(data);
                    }
                    return;
                }
                _success(data);
            },
            error: function error(XMLHttpRequest, textStatus, errorThrown) {
                layerTool.msg("请求失败" + errorThrown);
                if (tools.isFunction(_error)) {
                    _error();
                }
            },
            complete: function complete() {
                if (tools.isFunction(_complete)) {
                    _complete(option.id);
                } else {
                    layerTool.close(_loading);
                }
            }

        });
    }

    /**
     * 构造参数
     * @param param
     * @returns {{token: *, t: *, content: string, v: string}}
     * @private
     */
    function _constructParam(_ref) {
        var _ref2 = _slicedToArray(_ref, 3),
            serviceIName = _ref2[0],
            method = _ref2[1],
            param = _ref2[2];

        var arr = [serviceIName, method, param];
        var content = "";
        arr[3] = arr[2] || "";
        if (typeof arr[2] == "string") {
            content = arr[2];
        } else {
            content = JSON.stringify(arr[2]);
        }
        var pri_param = {
            serviceIName: arr[0],
            method: arr[1],
            currentUrl: window.location.href,
            content: tools.encrypt(content, "cyplath5")
        };
        return pri_param;
    }

    function _initOption(option) {
        _option = new Object();
        _option.isCheck = false;
        _option.loading = true;
        if (tools.isNotNull(option) && typeof option.isCheck != "undefined") {
            _option.isCheck = option.isCheck;
        }
        if (tools.isNotNull(option) && typeof option.loading != "undefined") {
            _option.loading = option.loading;
        }
    }

    return {
        /**
         *
         * @param transcode
         * @param param 参数对象
         * @param success 成功回调
         * @param error 失败回调
         * @param option 选项[object]
         * isCheck:是否进行表单验证 true|false 默认false
         * loading:是否加载弹出层 true|false 默认true
         */
        postTranscode: function postTranscode(url, _ref3, success, error, option) {
            var _ref4 = _slicedToArray(_ref3, 3),
                serviceIName = _ref4[0],
                method = _ref4[1],
                param = _ref4[2];

            var parameters = _constructParam([serviceIName, method, param]);
            if (!tools.isFunction(error)) {
                option = error;
            }
            _initOption(option);
            _post(url, parameters, success, error);
        }

    };
}();

/**
 * 弹出层帮助方法
 * @type {{loading, msg, close, closeAll}}
 */
var layerTool = function () {

    return {
        loading: function loading(m) {
            var msg = "努力加载中";
            if (typeof m != "undefined") {
                msg = m;
            }
            return layer.open({
                type: 2,
                content: msg,
                shade: 'background-color:transparent',
                shadeClose: false
            });
        },
        msg: function msg(_msg, callback) {
            return layer.open({
                content: _msg,
                skin: 'msg',
                time: 2 //2秒后自动关闭
                , end: function end() {
                    if (tools.isFunction(callback)) {
                        callback();
                    }
                }
            });
        },
        close: function close(index) {
            if (tools.isNotNull(index)) {
                layer.close(index);
            } else {
                layer.close(0);
            }
        },
        closeAll: function closeAll() {
            layer.closeAll();
        }
    };
}();
/**
 * 数据存储帮助方法
 * @type {{store, read, readOnce}}
 */
var dataTool = function () {

    return {
        store: function store(key, val) {
            val = JSON.stringify(val);
            localStorage.setItem(key, val);
        },
        read: function read(key) {
            var data = localStorage.getItem(key);
            if (tools.isNotNull(data)) {
                data = JSON.parse(data);
            }
            return data;
        },
        readOnce: function readOnce(key) {
            var data = localStorage.getItem(key);
            if (tools.isNotNull(data)) {
                data = JSON.parse(data);
            }
            localStorage.removeItem(key);
            return data;
        },
        remove: function remove(key) {
            localStorage.removeItem(key);
        }
    };
}();

/**
 * 页面初始化工具类
 * @type {{finish}}
 */
var pageInit = function () {
    var _initCallback = null;
    var _beforeCallback = null;

    $(function () {
        if (tools.isFunction(_beforeCallback)) {
            _beforeCallback();
        }
        if (tools.isFunction(_initCallback)) {
            _initCallback();
        }
    });
    return {
        before: function before(callback) {
            _beforeCallback = callback;
        },
        finish: function finish(callback) {
            _initCallback = callback;
        }
    };
}();
/**
 * vue下拉刷新+无限加载
 *<link rel="stylesheet" href="style/css/page.css">
 *  <script type="text/javascript" src="style/js/include.js"></script>
 *  <script src="thirdParty/jroll/jroll.js"></script>
 *  <script src="thirdParty/jroll/jroll-vue-infinite.js"></script>
 *  <script src="thirdParty/jroll/jroll-pulldown.1.0.0.js"></script>
 *  <jroll-infinite class="jroll-vue-infinite container">
 *      <ul></ul>
 *  </jroll-infinite>
 * @type {{init, option}}
 */
var page = function () {

    var _pullDownAction = null;
    var _pullUpAction = null;
    var _option = null;
    var _component = null;

    function _initStyle() {
        var jroll = $(".jroll-vue-infinite");
        var topOffset = $(jroll).offset().top;
        // var jroll_next = $("div[jroll-id*='scroller']");
        $(jroll).css({
            "height": "calc(100% - " + topOffset + "px)"
        });
        if (typeof _option.pullUpAction == "undefined") {
            $(".jroll-infinite-tip").hide();
        }
    }

    function initStyle() {
        pageInit.finish(function () {
            _initStyle();
        });
    }

    function bottomed(complete) {
        ;
        _component = this;
        var me = this;
        if (me.page == -1) {
            me.tip = "我是有底线的";
            me.tip = "";
            return;
        }
        if (typeof complete == "function") {
            console.log("下拉刷新");
            _pullDownAction(1, function (status) {
                if (status == 0) {
                    //me.tip = "我是有底线的";
                    me.tip = "";
                    me.page = -1;
                } else {
                    me.page = 1;
                    me.tip = "上拉加载更多数据";
                }
                if (tools.isFunction(complete)) {
                    complete();
                }
            });
        } else {
            me.tip = "正在加载中...";
            if (me.page == 0) {
                console.log("首次加载刷新");
                _pullDownAction(1, function (status) {
                    me.page++;
                    if (status == 0) {
                        //me.tip = "我是有底线的";
                        me.tip = "";
                        me.page = -1;
                    } else {
                        me.page = 1;
                        me.tip = "上拉加载更多数据";
                    }
                    if (tools.isFunction(complete)) {
                        complete();
                    }
                });
                return;
            }
            console.log("上拉加载");
            _pullUpAction(me.page, function (status) {
                if (status == 0) {
                    me.tip = "我是有底线的";
                    me.page = -1;
                } else {
                    me.tip = "上拉加载更多数据";
                    me.page++;
                }
            });
        }
    }

    var infOptions = {
        updated: function updated() {
            console.log('current page is ' + this.page);
        }
    };

    return {
        init: function init(option) {
            ;
            if (tools.isNotNull(infOptions.bottomed)) {
                bottomed(undefined);
            }
            _option = option;
            if (typeof option.pullUpAction != "undefined") {
                _pullUpAction = option.pullUpAction;
            }
            infOptions.bottomed = bottomed;

            if (typeof option.pullDownAction != "undefined") {
                infOptions.pulldown = {};
                _pullDownAction = option.pullDownAction;
            }
            initStyle();
        },
        /**
         * 重置组件
         * page = 0
         * 滚动到最顶部
         * 调用首次加载方法
         */
        reset: function reset() {
            _component.page = 0;
            _component.jroll.scrollTo(0, 0);
            infOptions.bottomed.call(_component);
        },
        end: function end(status) {
            if (tools.isNull(_component)) {
                return;
            }
            if (status == 0) {
                // _component.tip = "我是有底线的";
                _component.tip = "";
                _component.page = -1;
            } else if (status == 1) {
                _component.tip = "上拉加载更多";
                _component.page = 1;
            } else {
                _component.tip = "未知错误";
                _component.page = 1;
            }
        },
        option: infOptions,
        jrollOption: {
            id: "scroller",
            scrollBarY: true
        }
    };
}();