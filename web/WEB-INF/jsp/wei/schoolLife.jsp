<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/9
  Time: 9:37
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/weui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/jquery-weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/demos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/schoolLife.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/jquery-weui.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container" id="listLoad">

    <div class="weui-infinite-scroll" style="display: none">
        <div class="infinite-preloader"></div>
        正在加载
    </div>
    <!--//内容滚动区域底部留白-->
    <div class="marginBottom"></div>
</div>
<div class="header">
    <a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-arrow-left"></span></a>
    校园生活
</div>
<div class="bottom">
    <%--底部--%>
</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/schoolLife.js"></script>
</html>