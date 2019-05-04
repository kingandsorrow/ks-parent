/**
 *隐藏网页右上角的按钮
 */
function hideShare() {
    function onBridgeReadyHide() {
        WeixinJSBridge.call('hideOptionMenu');
    }

    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReadyHide, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReadyHide);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReadyHide);
        }
    } else {
        onBridgeReadyHide();
    }
}
/**
 * 微信分享
 * @return
 */
function weixinShare(localUrl, partnerId, title, descContent, share_url, imgUrl, extInfo, successCallback) {
    // 解决苹果分享的问题，分享的连接地址不能包含端口号
    share_url = share_url.replace(":80", "");
    if (!tools.isWeiXin()) {
        return;
    }
	var http = window.location.protocol + "//" +window.location.host;
    $.ajax({
        url: http + "/cy-plat-h5/weixin_weixinShare.do",
        type: "POST",
        data: {
            url: localUrl,
            partnerId: partnerId,
            host: window.location.host
        },
        dataType: 'json',
        timeout: 3000,
        success: function (data) {
            if (!data) {
                return false;
            }
            var url = data.url;
            var jsapiTicket = data.jsapiTicket;
            var nonceStr = data.nonceStr;
            var timeStamp = data.timeStamp;
            var signature = data.signature;
            var appId = data.appId;


            wx.config({
                debug: false,
                appId: appId,
                timestamp: timeStamp,// 生成signature的时间戳
                nonceStr: nonceStr,// 生成signature的随机串
                signature: signature,
                jsApiList: ['checkJsApi', 'onMenuShareTimeline',
                    'onMenuShareAppMessage', 'onMenuShareQQ',
                    'onMenuShareWeibo', 'hideMenuItems', 'chooseWXPay']
            });

            wx.ready(function () {
                var shareData = {
                    title: title,
                    desc: descContent,
                    link: share_url,
                    imgUrl: imgUrl,
                    success: function () {
                        //用户分享成功后执行的回调函数
                        if (extInfo.shareType == "sendFlow") {
                            //跳转发送红包详情页面
                            window.location.href = 'redPackNotes_sendFlowDetail.do?orderId=' + extInfo.donateOrderId + "&descContent=" + encodeURI(encodeURI(descContent)) + "&title=" + encodeURI(encodeURI(title));
                        }
                        if (extInfo.shareType == "buyShare") {
                            //进行分享成功记录
                            $.ajax({
                                url: 'myOrder_addShareRecord.do',
                                data: {
                                    'order_id':extInfo.donateOrderId,
                                    'ruleId':extInfo.ruleId,
                                    'buyTime':extInfo.buyTime
                                },
                                dataType: 'json',
                                type: 'post',
                                success: function (data) {
                                    
                                }
                            });
                            
                            
                        }
                        if (typeof successCallback != "undefined") {
                            successCallback();
                        }
                    },
                    cancel: function (res) {
                        // 用户取消分享后执行的回调函数
                    }
                };

                wx.onMenuShareQQ(shareData);//qq好友
                wx.onMenuShareWeibo(shareData);//腾讯微博
                wx.onMenuShareTimeline(shareData);//朋友圈
                wx.onMenuShareAppMessage(shareData);//微信好友
                //屏蔽右上角功能，只剩 分享给朋友和朋友圈
                wx.hideMenuItems({
        	      menuList: [
        	                 "menuItem:share:qq","menuItem:share:weiboApp","menuItem:favorite","menuItem:share:facebook",
        	                 "menuItem:share:QZone","menuItem:editTag","menuItem:delete","menuItem:copyUrl","menuItem:originPage",
        	                 "menuItem:readMode","menuItem:openWithQQBrowser","menuItem:openWithSafari","menuItem:share:email","menuItem:share:brand"
        	      ],
        	      success: function (res) {
        	        
        	      },
        	      fail: function (res) {
        	       
        	      }
        	    });
            });

            wx.error(function (res) {
                //alert(res.errMsg);
            });
        }
    });
}


