var httpRequest = (function () {
    const http = axios.create({
        /*baseURL: CONSTANT.BASE_URI,*/
        timeout: 1000 * 30,
        withCredentials: true,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        }
    })
    http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    // 添加请求拦截器
    http.interceptors.request.use(function (config) {
        // 在发送请求之前做些什么

        /*var JSESSIONID = getCookie("JSESSIONID");
        var token = getCookie("token");*/
        console.log("cookie token..", "111");
        /*config.headers['token'] = token;*/
        /*console.log("request is.." + config);*/
        return config;
    }, function (error) {
        console.log("request error:" + error);
        /*return Promise.reject(error);*/
    });
    // 添加响应拦截器
    http.interceptors.response.use(function (response) {
        // 对响应数据做点什么
        /*var type = typeof response.data;
        var data = JSON.stringify(response.data);
        if (response.data.errCode == "30012") {
            window.location.href = response.data.loginUrl + "?redirect_url=" + encodeURI(window.location.href);
            return;
        }*/
        console.log("result::" + response);
        return response;
    }, function (error) {
        // 对响应错误做点什么
        alert("result error:" + error);
        console.log("result error:" + error);
        return Promise.reject(error);
    });
    return {
        getTranscode: function getTranscode(url, param, successFunction, errorFunction) {
            http.get(CONSTANT.BASE_URI + url, {params: param}).then(function (response) {
                successFunction(response)
            }).catch(function (error) {
                errorFunction(error)
            });
        },
        postTranscode: function postTranscode(url, param, successFunction, errorFunction) {
            http.post(CONSTANT.BASE_URI + url, param).then(function (response) {
                successFunction(response)
            }).catch(function (error) {
                errorFunction(error)
            });
        }
    }
})()
