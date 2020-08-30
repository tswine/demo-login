var tool = {
    success: 200,
    basrUrl: "http://localohost",
    /*request 请求*/
    request: function(url, type, params, fun) {
        var _this = this;
        $.ajax({
            url: this.basrUrl + url,
            type: type,
            dataType: 'json',
            data: params,
            error: function(err) {
                console.log(err);
                window.alert("未知异常");
            },
            success: function(data) {
                var status = data.status;
                if (_this.success == status) {
                    fun(data.data);
                } else {
                    window.alert(data.msg);
                    var code = data.code;
                    if (code === 403) {
                        window.location.href = "login.html";
                    }
                }
            }
        });
    },
    /*获取浏览器参数*/
    getRequestParam: function(param) {
        var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return "";
    },
    /* 判断字符串是否为空字符串*/
    isNotNull: function(str) {
        if (typeof str === 'undefined') { //先判断类型
            return false;
        } else if (str == null) {
            return false;
        } else if (str == '') {
            return false;
        }
        return true;
    }
}