<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术教育</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/classPlan.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
     <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container">
    <div class="dataBanner">
        <div class="month" style="width: 9%;">
            <p id="monthNum"></p>
            <p>月</p>
        </div>
        <div style="width: 7%"><span class="glyphicon glyphicon-menu-left" id="before"></span></div>
        <div class="day">
            <p class="dayNum day0"></p>
            <p class="week">周一</p>
        </div>
        <div class="day">
            <p class="dayNum day1"></p>
            <p class="week">周二</p>
        </div>
        <div class="day">
            <p class="dayNum day2"></p>
            <p class="week">周三</p>
        </div>
        <div class="day">
            <p class="dayNum day3"></p>
            <p class="week">周四</p>
        </div>
        <div class="day">
            <p class="dayNum day4"></p>
            <p class="week">周五</p>
        </div>
        <div class="day">
            <p class="dayNum day5"></p>
            <p class="week">周六</p>
        </div>
        <div class="day">
            <p class="dayNum day6"></p>
            <p class="week">周日</p>
        </div>
        <div style="width: 7%"><span class="glyphicon glyphicon-menu-right" id="after"></span></div>
    </div>
    <div class="plan">
        <!--月份下面空白-->
        <div style="width: 9%"></div>
        <!--向前按钮下面空白-->
        <div style="width: 7%"></div>

        <!--周一-->
        <div class="day0_ dayclass">

        </div>

        <!--周二-->
        <div class="day1_ dayclass">

        </div>

        <!--周三-->
        <div class="day2_ dayclass">

        </div>

        <!--周四-->
        <div class="day3_ dayclass">

        </div>

        <!--周五-->
        <div class="day4_ dayclass">

        </div>

        <!--周六-->
        <div class="day5_ dayclass">

        </div>

        <!--周日-->
        <div class="day6_ dayclass">

        </div>

        <!--向后按钮下面空白-->
        <div style="width: 7%;"></div>
    </div>
</div>
<div class="header">
    <a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-arrow-left"></span></a>
    课程表
</div>
<div class="bottom">

</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/classTable.js"></script>
</html>