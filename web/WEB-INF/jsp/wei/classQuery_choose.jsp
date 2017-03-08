<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/2/22
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术培训中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/classQuery.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container">
    <p class="please">请选择查询的课程</p>
    <%--通过js ajax 添加课程--%>
</div>
<div class="header">
    <a href="index.html"><span class="icon-icon15"></span></a>
    课程查询
</div>
<div class="bottom">
    <a href="index.html">
        <div class="col-xs-4" id="index">
                <span class="icon-icon1">
                </span>
            <p>首页</p>
        </div>
    </a>
    <a href="fee_chooseClass.html">
        <div class="col-xs-4" id="fee">
                <span class="icon-icon2">
                </span>
            <p>缴费</p>
        </div>
    </a>
    <a href="#" id="tel">
        <div class="col-xs-4" id="phone">
                <span class="icon-icon3">
                </span>
            <p>电话</p>
        </div>
    </a>
</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/classQuery_choose.js"></script>
</html>