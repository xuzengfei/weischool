<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/3
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术教育</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/weui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/jquery-weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/inform.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/jquery-weui.js"></script>
     <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>

</head>
<body>
<div class="container">
    <a >
        <div class="headTitle">
            <img src="">
            <p><%--加载标题--%></p>
        </div>
    </a>
    <div class="list-group">
        <%--加载公告列表--%>
    </div>
    <div class="weui-infinite-scroll" style="display: none">
        <div class="infinite-preloader"></div>
        正在加载
    </div>
</div>
<div class="header">
    <a href="index.html"><span class="glyphicon glyphicon-arrow-left"></span></a>
    公告
</div>
<div class="bottom">
</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>

<script src="${pageContext.request.contextPath}/rs/js/wei/inform.js"></script>
</html>