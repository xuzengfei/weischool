<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/2/22
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/classQuery.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/jquery-weui.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>
</head>
<body ontouchstart>
<div class="container">
    <div class="classTop">
        <img class="img-circle" src="">
        <h3></h3>
        <h5></h5>
        <h4>剩余课时</h4>
        <h2>0</h2>
        <a class=" open-popup" data-target="#half">
            <span class="icon-icon12"></span>
        </a>
    </div>
    <div class="list-group" id="listLoad1">
        <%--此处加载上课记录信息--%>
    </div>
    <div class="weui-infinite-scroll" style="display: none">
        <div class="infinite-preloader"></div>
        正在加载
    </div>

    <!--//内容滚动区域底部留白-->
    <div class="marginBottom"></div>
</div>
<div class="bottom">

</div>
<form id="half" class='weui-popup-container popup-bottom'>
    <div class="weui-popup-modal">
        <div class="toolbar">
            <div class="toolbar-inner">
                <a href="javascript:void(0);" class="picker-button close-popup" style="color: #1bb9e0;">关闭</a>
                <h1 class="title">搜索</h1>
            </div>
        </div>
        <div class="modal-content">
            <input type="date" class="dateInput" id="start" placeholder="请输入起始时间">&nbsp;——
            <input type="date" class="dateInput" id="end" placeholder="请输入截止时间">
            <br>
            <div class="checkbox">
                <label class="checkbox">
                    <input type="radio" value="1" name="status">
                    准时状态
                </label>
            </div>
            <div class="checkbox">
                <label>
                    <input type="radio" value="2"  name="status">
                    请假状态
                </label>
            </div>
            <div class="checkbox">
                <label>
                    <input type="radio" value="-1"  name="status">
                    旷课状态
                </label>
            </div>
            <button type="button" id="btn">确定</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
    var moduleId ='${gradId}';
    var sgId ='${sgId}';

</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/classQuery.js"></script>
</html>