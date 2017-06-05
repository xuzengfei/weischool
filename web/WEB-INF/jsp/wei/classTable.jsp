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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/classTable.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container">
    <div class="tableTop">
        <div class="mouth"><span></span><br>月</div>
        <div class="tableDate">
            <p class="day day0"></p>
            <p class="weekNum">周一</p>
        </div>
        <div class="tableDate">
            <p class="day day1"></p>
            <p class="weekNum">周二</p>
        </div>
        <div class="tableDate">
            <p class="day day2"></p>
            <p class="weekNum">周三</p>
        </div>
        <div class="tableDate">
            <p class="day day3"></p>
            <p class="weekNum">周四</p>
        </div>
        <div class="tableDate">
            <p class="day day4"></p>
            <p class="weekNum">周五</p>
        </div>
        <div class="tableDate">
            <p class="day day5"></p>
            <p class="weekNum">周六</p>
        </div>
        <div class="tableDate">
            <p class="day day6"></p>
            <p class="weekNum">周日</p>
        </div>
    </div>
    <div class="tableClass">
        <div class="classDetail day0_">

        </div>
        <div class="classDetail day1_">

        </div>
        <div class="classDetail day2_">

        </div>
        <div class="classDetail day3_">

        </div>
        <div class="classDetail day4_">

        </div>
        <div class="classDetail day5_">

        </div>
        <div class="classDetail day6_">

        </div>
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