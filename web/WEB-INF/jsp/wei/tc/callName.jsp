<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术培训中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/iconStyle_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/icon_other.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/teacher.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/layer/1.9.3/layer.js"></script>
</head>
<body>
<div class="container">
    <div class="list-group callNameList">
    </div>
</div>
<div class="header">
    <a href="javascript:history.go(-1)"><span class="icon-icon15"></span></a>
    <p>${gradeName}</p>
</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
    var gradeId ="${gradeId}";
    var gtId ="${gtId}";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/tc/callname.js"></script>
</html>