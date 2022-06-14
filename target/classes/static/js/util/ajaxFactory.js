(function ($, myWindow) {
    var $z = {

        SUCCCSS_CODE: 200,
        SC_UNAUTHORIZED_CODE: 401,
        /**
         * 处理返回信息的公共方法
         */
        dealCommonResult: function dealCommonResult(data, func) {
            if (data.code === $z.SUCCCSS_CODE) {
                func();
            } else {
                layer.alert("<em style='color:black'>" + data.msg + "</em>", {
                    icon: 5,
                    offset: "200px",
                    title: '错误'
                });
                // 进度条消失
                MprogressEnd();
	//如果是重新登录，跳到登录页面
                if (data.code === $z.SC_UNAUTHORIZED_CODE) {
                    var loginUrl = basePath + "/views/login.html";
                    setTimeout("window.location.href = '" + loginUrl + "'", 1500);
                }
            }
        },
        // RequestBody接收 async(是否异步)
        ajaxPost: function ajaxPost(allData) {
            var token = sessionStorage.getItem(TOKEN);
            $.ajax({
                url: allData.url,
                type: "post",
                headers: {
                    token: token ? token : "",
                    Accept: "application/json; charset=utf-8"
                },
                async: allData.async,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(allData.data),
                beforeSend: allData.beforeSend,
                success: allData.success,
                complete: function (XMLHttpRequest) {
                },
                error: function (data, status, e) {
                    layer.alert("<em style='color:black'>" + (data.responseJSON.msg ? data.responseJSON.msg : data.responseJSON.error) + "</em>", {
                        icon: 5,
                        offset: "200px",
                        title: '错误'
                    });
                    // 进度条消失
                    MprogressEnd();
                }
            });
        },
        // 普通参数或对象接收
        ajaxGet: function ajaxGet(allData) {
            var token = sessionStorage.getItem(TOKEN);
            $.ajax({
                url: allData.url,
                type: "get",
                headers: {
                    token: token ? token : "",
                    Accept: "application/json; charset=utf-8"
                },
                async: allData.async,
                data: allData.data,
                beforeSend: allData.beforeSend,
                success: allData.success,
                complete: function (XMLHttpRequest) {
                },
                error: function (data, status, e) {
                    layer.alert("<em style='color:black'>" + (data.responseJSON.msg ? data.responseJSON.msg : data.responseJSON.error) + "</em>", {
                        icon: 5,
                        offset: "200px",
                        title: '错误'
                    });
                    // 进度条消失
                    MprogressEnd();
                }
            });
        },
        downLoadExcel: function downLoadExcel(filePath) {
            // alert(filePath);
            window.parent.location.href = '/excel/downloadfile?filePath='
                + encodeURI(filePath);
        }

    };
    myWindow.$z = $z;
})(jQuery, window);