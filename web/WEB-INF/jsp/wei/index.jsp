<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/2/16
  Time: 9:24
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/main.css">
    <script src="${pageContext.request.contextPath}/rs/js/wei/fastclick.js"></script>
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body>
<div class="container">
    <div class="top">
        <img class="img-circle" src="">
        <div class="userMsg">
            <div class="col-xs-4">姓名:<h5 id="stName"></h5></div>
            <div class="col-xs-4">班级:<h5 id="gradeName">无</h5></div>
            <div class="col-xs-4">积分:<h5 id="total">0</h5></div>
        </div>
        <div class="last_class">剩余<p id="restClass">0</p>节课</div>
        <div class="area">
           <%-- <img src="${pageContext.request.contextPath}/rs/css/wei/images/index_point.png"><span id="cpName"></span>--%>
            <!--校区选择-->
            <div class="dropdown">
                <a href="#" class="dropdown-toggle"  id="cpName" data-toggle="dropdown">校区选择<b class="caret"></b></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                   <%-- <li><a href="#" tabindex="-1" data-toggle="tab" >火炬校区</a></li>
                    <li><a href="#" tabindex="-1" data-toggle="tab"  >南朗校区</a></li>--%>
                </ul>
            </div>
            <!--校区选择-->
        </div>
    </div>
    <div class="middle">
        <a href="javascript:void(0)">
            <div class="col-xs-4" id="classQuery">
                <span class="icon-icon5">
                </span>
                <p>课程查询</p>
            </div>
        </a>
        <a href="javascript:void(0);">
            <div class="col-xs-4" id="cordQuery">
                <span class="icon-icon6">
                </span>
                <p>成绩查询</p>
            </div>
        </a>
        <a href="javascript:void(0);">
            <div class="col-xs-4" id="certificate">
                <span class="icon-icon8">
                </span>
                <p>证书查询</p>
            </div>
        </a>
        <a href="javascript:void(0)">
            <div class="col-xs-4" id="album">
                <span class="icon-icon7">
                </span>
                <p>纪念册</p>
            </div>
        </a>
        <a href="javascript:void(0)">
            <div class="col-xs-4" id="schoolLife">
                <span class="icon-icon4">
                </span>
                <p>校园生活</p>
            </div>
        </a>
        <a href="javascript:void(0);">
            <div class="col-xs-4" id="inform">
                <span class="icon-icon9">
                </span>
                <p>公告</p>
            </div>
        </a>
        <a href="javascript:void(0);">
            <div class="col-xs-4" id="about">
                <span class="icon-icon10">
                </span>
                <p>关于</p>
            </div>
        </a>
        <a href="javascript:void(0);">
            <div class="col-xs-4" id="classTable">
                <span class="icon-icon11">
                </span>
                <p>课程表</p>
            </div>
        </a>
    </div>
</div>
<div class="bottom"></div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/index.js"></script>


</html>