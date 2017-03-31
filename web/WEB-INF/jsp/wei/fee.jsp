
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术教育</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/fee.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container">
    <div class="feeDetail">
        <div class="line1">
            <p class="td1">您已选择：</p>
            <p class="td2"></p>
        </div>
        <div class="line2">
            <p class="td3"></p>
            <p class="td4">0课时</p>
        </div>
        <div class="line3">
            <p class="td5">合计：</p>
            <p class="td6">￥0</p>
        </div>
    </div>
    <form class="feeCheck">
        <div class="fee">
            <p class="feeItem">选择优惠券</p>
            <select  onchange="chooseConpus(this)">
                <option value="-1">--代金券选择--</option>
                <!--<option>100元现金券</option>-->
                <!--<option>8折优惠券</option>-->
                <!--<option>5折优惠券</option>-->
            </select>
        </div>
        <div class="trueFee">
            <p class="feeItem">实付款</p>
            <p class="trueFee_p">￥0元</p>
        </div>
        <button type="submit">确认</button>
    </form>
</div>
<div class="header">
    <a href="javascript:history.go(-1)"><span class="icon-icon15"></span></a>
    缴费
</div>
<div class="bottom">

</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
    var gradeId ='${gradeId}';
    var cosId ='${cosId}';
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/free.js"></script>
</html>