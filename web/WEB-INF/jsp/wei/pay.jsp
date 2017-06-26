<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/6/8
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head>
    <meta charset="utf-8">
    <title>微信JS-SDK Demo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/lib/pay/style.css">
</head>
<body ontouchstart="">
<div class="wxapi_container">
    <div class="wxapi_index_container">
        <ul class="label_box lbox_close wxapi_index_list">
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-basic">基础接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-webview">界面操作接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-scan">微信扫一扫接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-pay">微信支付接口</a></li>
        </ul>
    </div>
    <div class="lbox_close wxapi_form">
        <h3 id="menu-basic">基础接口</h3>
        <span class="desc">判断当前客户端是否支持指定JS接口</span>
        <button class="btn btn_primary" id="checkJsApi">checkJsApi</button>

        <h3 id="menu-webview">界面操作接口</h3>
        <span class="desc">隐藏右上角菜单接口</span>
        <button class="btn btn_primary" id="hideOptionMenu">hideOptionMenu</button>
        <span class="desc">显示右上角菜单接口</span>
        <button class="btn btn_primary" id="showOptionMenu">showOptionMenu</button>
        <span class="desc">隐藏头部</span>
        <button class="btn btn_primary" id="WeixinJSBridgeReady">WeixinJSBridgeReady</button>



        <h3 id="menu-scan">微信扫一扫</h3>
        <span class="desc">调起微信扫一扫接口</span>
        <button class="btn btn_primary" id="scanQRCode0">scanQRCode(微信处理结果)</button>
        <button class="btn btn_primary" id="scanQRCode1">scanQRCode(直接返回结果)</button>

        <h3 id="menu-pay">微信支付接口</h3>
        <span class="desc">发起一个微信支付请求</span>
        <button class="btn btn_primary" onclick="callpay()">chooseWXPay</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/rs/lib/pay/jweixin-1.0.0.js"></script>
<script>
    /*
     * 注意：
     * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
     * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
     * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
     *
     * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
     * 邮箱地址：weixin-open@qq.com
     * 邮件主题：【微信JS-SDK反馈】具体问题
     * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
     */
    $(function () {
         $.getJSON("${pageContext.request.contextPath}/wei/st/pay/jssdk/congfig",function (rs) {
              if(rs.success){
                 var data = rs.obj;
                 wx.config({
                     debug: false,
                     appId: data.appId,
                     timestamp: data.timestamp,
                     nonceStr: data.nonceStr,
                     signature:data.signature,
                     jsApiList: [
                         'checkJsApi',
                         'hideOptionMenu',
                         'showOptionMenu',
                         'scanQRCode',
                         'chooseWXPay'
                     ]
                 });



             }else{
                 alert(rs.msg);
             }
         })
    })

    function onBridgeReady(){
        $.post("${pageContext.request.contextPath}/wei/st/pay/jssdk/paycfg",{fee:"1"},function (rs) {
            if(rs.success){
                var data = rs.obj
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId":data.appId,     //公众号名称，由商户传入
                        "timeStamp":data.timeStamp,         //时间戳，自1970年以来的秒数
                        "nonceStr":data.nonceStr, //随机串
                        "package":data.package,
                        "signType":data.signType,         //微信签名方式：
                        "paySign":data.paySign //微信签名
                    },
                    function(res){
                        if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                            alert(1);
                        }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    }
                );
            }else{
                alert(rs.msg);
            }
        },"json");

    }
    function callpay() {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    }
</script>
<script src="${pageContext.request.contextPath}/rs/lib/pay/zepto.min.js"></script>
<script src="${pageContext.request.contextPath}/rs/lib/pay/demo.js"> </script>
</body>
</html>