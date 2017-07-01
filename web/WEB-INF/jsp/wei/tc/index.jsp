
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术教育</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/teacher.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
     <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container">
    <div class="classTop">
        <img class="img-circle" src="${pageContext.request.contextPath}/rs/css/wei/images/loginImg.png">
        <h3></h3>
        <h5></h5>
        <h4></h4>
    </div>
    <div class="list-group" id="nulldata">
        <p class="time">暂无数据</p>
    </div>
   <%-- <div class="list-group">
        <div class="addressChoose">
            <img src="${pageContext.request.contextPath}/rs/css/wei/images/teach_point.png" class="point">
            <p>五桂山校区</p>
        </div>
        <div class="list-group-item">
            <button class="statue">课程结束</button>
            <p class="className">跆拳道初级班</p>
            <p class="time">周六 08:30-10:00</p>
            <a href="teach_callName.html"><button class="callNameBtn">点名</button></a>
        </div>
        <div class="list-group-item">
            <button class="statue">正在上课</button>
            <p class="className">跆拳道初级班</p>
            <p class="time">周六 08:30-10:00</p>
            <a href="teach_callName.html"><button class="callNameBtn" >点名</button></a>
        </div>
    </div>--%>

   <%-- <div class="list-group">
        <div class="addressChoose">
            <img src="${pageContext.request.contextPath}/rs/css/wei/images/teach_point.png" class="point">
            <p>小榄校区</p>
        </div>
        <div class="list-group-item">
            <button class="statue">还未开始</button>
            <p class="className">跆拳道初级班</p>
            <p class="time">周六 08:30-10:00</p>
            <a href="teach_callName.html"><button class="callNameBtn">点名</button></a>
        </div>
        <div class="list-group-item">
            <button class="statue">课程取消</button>
            <p class="className">跆拳道初级班</p>
            <p class="time">周六 08:30-10:00</p>
            <a href="teach_callName.html"><button class="callNameBtn" >点名</button></a>
        </div>
    </div>--%>
</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/tc/index.js"></script>
</html>