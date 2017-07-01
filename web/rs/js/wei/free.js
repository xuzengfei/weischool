$(function () {
    getData(); //列出
})
var amount = 0;
/**
 * 列出用户
 */
function getData() {
    $.getJSON(basePath + "wei/st/pay/grade/" + gradeId + "/costtpl/" + cosId + "/free/data", function (res) {
        if (res.success) {
            var data = res.attributes;
            amount =parseInt(data.amount);
            $(".d2").text(data.gradeName);
            $(".td4").text(data.classNum + "课时");
            $(".td6").text("￥" + data.amount);
            $(".trueFee_p").text("￥" + amount);
            $.each(data.coupons, function (i, item) {
                $(".fee select").append("<option  value=\"" + item.id + "\" _num=\""+item.useRule+"\" >" + item.useRule + "元现金券</option>");
            })
        }
    });
}
function chooseConpus(elemnt) {
    var  conpus =elemnt.value;
    if(conpus!="-1"){
       var nowAmo = amount- parseInt($(".fee select").find("option:selected").attr("_num"));
        nowAmo=nowAmo<=0?0:nowAmo;
        $(".trueFee_p").text("￥" + nowAmo);
    }else{
        $(".trueFee_p").text("￥" + amount);
    }
}
/**
 * 微信jssdk 绑定
 * @param jsApiList 传入数组 ,需要使用的JS接口列表
 */
$(function () {
    $.getJSON(basePath+"wei/st/pay/jssdk/congfig",{url:window.location.href},function (rs) {
        if(rs.success){
            var data = rs.obj;
            wx.config({
                debug: false,
                appId: data.appId,
                timestamp: data.timestamp,
                nonceStr: data.nonceStr,
                signature:data.signature,
                jsApiList:[
                    'hideOptionMenu',
                    'scanQRCode',
                    'chooseWXPay'
                ]
            });
        }else{
            alert(rs.msg);
        }
    })
})

/**
 * 隐藏微信操作功能
 */

wx.ready(function () {
    wx.hideOptionMenu();
    document.querySelector('#chooseWXPay').onclick = function () {
        $.getJSON(basePath +"wei/st/pay/cosId/"+cosId,{coupId:$(".fee select").val()},function (rs) {
            if(rs.success) {
                var data = rs.obj;
                wx.chooseWXPay({
                    timestamp: data.timeStamp,
                    nonceStr: data.nonceStr,
                    package: data.package,
                    signType:data.signType, // 注意：新版支付接口使用 MD5 加密
                    paySign: data.paySign,
                    success: function (res) {
                        if (res.errMsg == "chooseWXPay:ok") {
                            //支付成功
                            $.post(basePath +"wei/st/pay/cosId/"+cosId,{coupId:$(".fee select").val(),gradeId:gradeId},function (result) {
                                    if(!result.success){
                                        layer.open({
                                            content: "数据故障，请联系前台人员！"
                                            ,skin: 'msg'
                                            ,time: 2 //2秒后自动关闭
                                        });
                                    }else{
                                        history.go(-1);
                                    }

                            },"json");
                        } else {
                            layer.open({
                                content: res.errMsg
                                ,skin: 'msg'
                                ,time: 2 //2秒后自动关闭
                            });
                        }
                    }
                });
            }else{
                if(rs.obj==1){
                    //支付成功
                    $.post(basePath +"wei/st/pay/cosId/"+cosId,{coupId:$(".fee select").val(),gradeId:gradeId},function (result) {
                        if(!result.success){
                        layer.open({
                            content: "数据故障，请联系前台人员！"
                            ,skin: 'msg'
                            ,time: 2 //2秒后自动关闭
                        });
                        } else{
                            layer.open({
                                content: "支付成功"
                                ,skin: 'msg'
                                ,time: 2 //2秒后自动关闭
                            });
                            setTimeout(goback,2000);

                        }
                    },"json");
                }else{
                    layer.open({
                        content: rs.msg
                        ,skin: 'msg'
                        ,time: 2 //2秒后自动关闭
                    });
                }

            }
        });
    };
})

function goback() {
    history.go(-1);
}
/**
 * 调起微信支付
 */
/*wx.ready(function () {

})*/
/*

$(function () {
    $("#chooseWXPay").click(function () {
        $.post(basePath +"wei/st/pay/cosId/"+cosId,{coupId:$(".fee select").val(),gradeId:gradeId},function (result) {
            layer.open({
                content: "数据故障，请联系前台人员！"
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
            setTimeout(goback,2000);
        },"json");
    })
})
*/
