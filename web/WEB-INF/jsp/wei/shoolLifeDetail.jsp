<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/7
  Time: 15:47
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/certificate.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>

</head>
<body>
<div class="container">
    <p class="certificateTitle"></p>
    <p class="certificateDate"></p>
    <img class="certificateImg" src="">
    <div class="certificateContent">
        <%--此处输入填写内容--%>
    </div>

    <!--//内容滚动区域底部留白-->
    <div class="marginBottom"></div>
</div>
<div class="header">
    <a href="javascript:history.go(-1)"><span class="icon-icon15"></span></a>
    校园生活
</div>
<div class="bottom"><%--插入底部--%></div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
    var moduleId ='${id}';
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/schoolLifeDetail.js"></script>
</html>