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
    <title>玲珑艺术培训中心</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/cordQuery.css">
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
        <h4>考核状态</h4>
        <h2></h2>
        <a class=" open-popup" data-target="#half">
            <span class="icon-icon12"></span>
        </a>
    </div>
    <div class="list-group" id="listLoad">
        <%--<div class="list-group-item">
            <p class="itemTitle">第三次单位晋级考试</p>
            <p class="date">2016-11-28</p>
            <p class="detail">总体不错，力量有待加强</p>
            <h2 class="cord">通过</h2>
        </div>
        <div class="list-group-item">
            <p class="itemTitle">第二次单位晋级考试</p>
            <p class="date">2016-10-28</p>
            <p class="detail">总体不错，力量有待加强</p>
            <h2 class="cord">未通过</h2>
        </div>
        <div class="list-group-item">
            <p class="itemTitle">第二次单位晋级考试</p>
            <p class="date">2016-10-28</p>
            <p class="detail">总体不错，力量有待加强</p>
            <h2 class="cord">未通过</h2>
        </div>
        <div class="list-group-item">
            <p class="itemTitle">第二次单位晋级考试</p>
            <p class="date">2016-10-28</p>
            <p class="detail">总体不错，力量有待加强</p>
            <h2 class="cord">未通过</h2>
        </div>
        <div class="list-group-item">
            <p class="itemTitle">第二次单位晋级考试</p>
            <p class="date">2016-10-28</p>
            <p class="detail">总体不错，力量有待加强</p>
            <h2 class="cord">未通过</h2>
        </div>
        <div class="list-group-item">
            <p class="itemTitle">第三次单位晋级考试</p>
            <p class="date">2016-11-28</p>
            <p class="detail">总体不错，力量有待加强</p>
            <h2 class="cord">通过</h2>
        </div>--%>
    </div>
    <div class="weui-infinite-scroll" style="display: none">
        <div class="infinite-preloader"></div>
        正在加载
    </div>

    <div class="marginBottom"></div>
</div>
<div class="bottom">
     <%--底部信息--%>
</div>
<form id="half" class='weui-popup-container popup-bottom'>
    <div class="weui-popup-modal">
        <div class="toolbar">
            <div class="toolbar-inner">
                <a href="javascript:;" class="picker-button close-popup" style="color: #1bb9e0;">关闭</a>
                <h1 class="title">搜索</h1>
            </div>
        </div>
        <div class="modal-content">
            <input type="date" id="start" class="dateInput" placeholder="请输入起始时间">&nbsp;——
            <input type="date" id="end" class="dateInput" placeholder="请输入截止时间">
            <br>
            <div class="checkbox">
                <label class="checkbox">
                    <input type="radio"  name="status"  value="1">
                    通过状态
                </label>
            </div>
            <div class="checkbox">
                <label>
                    <input type="radio" name="status" value="0">
                    未通过状态
                </label>
            </div>
            <button type="button" class="btn" id="btn">确定</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
    var moduleId ='${id}';
</script>

<script src="${pageContext.request.contextPath}/rs/js/wei/cordQuery.js"></script>
</html>