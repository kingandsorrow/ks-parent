/**
 * Created by Administrator on 2017-08-29.
 */
var system = (function () {
    return {
        show: function (callback) {
            this.ticket = dataTool1.getCookie("ticket");
            if (tools.isNull(this.ticket)) {
                httpTool.postTranscode(BASE_URL, [
                    "userSignServiceI",
                    "getUserSignStatInfo",
                ], function (data) {
                    this.loading = false;
                }, function (err) {
                    this.loading = false;
                    if (err.errCode == "9964") {
                        window.location.href = err.ssoLoginInUrl;
                    }
                });
            } else {
                //登录之后拿ticket取userId
                httpTool.postTranscode(BASE_URL, [
                    "ssoLoginServiceI",
                    "getUserByToken",
                    this.ticket
                ], function (data) {
                    vm.loading = false;
                    vm.userInfo = data;
                    vm.userId = data.userId;
                    console.log(vm.userId);
                    //获取userId之后的操作
                    if (tools.isNotNull(callback)) {
                        callback();
                    }
                },function(err){
                	httpTool.postTranscode(BASE_URL, [
	                    "userSignServiceI",
	                    "getUserSignStatInfo",
	                ], function (data) {
	                    this.loading = false;
	                }, function (err) {
	                    this.loading = false;
	                    if (err.errCode == "9964") {
	                        window.location.href = err.ssoLoginInUrl;
	                    }
	                });
                });
            }
        }
    }
})()