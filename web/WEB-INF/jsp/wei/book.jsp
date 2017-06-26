<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/7
  Time: 17:14
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/book.css">
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/2.4.1/jquery-2.1.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/jquery-weui.js"></script>
    <script src="${pageContext.request.contextPath}/rs/js/wei/main.js"></script>

</head>
<body ontouchstart>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="margin-right: 10px;">&times;</span></button>
            <img src="#" class="modalImg">
        </div>
    </div>
</div>


<!-- Modal -->
<div class="container" id="listLoad">
    <%--<div class="list">
        <div class="dateBox">
            <img src="images/littlePoint.png">
            <p class="date">1月12日</p>
        </div>
        <div class="listContent">
            <p>昨天圣诞节表演小飞很出色哦！</p>
            <img src="images/bookImg1.png">
            <img src="images/bookImg2.png">
            <img src="images/bookImg1.png">
        </div>
    </div>
    <div class="list" >
        <div class="dateBox">
            <img src="images/littlePoint.png">
            <p class="date">1月12日</p>
        </div>
        <div class="listContent">
            <p>刻苦的训练，受到教练的表扬呢，继续加油</p>
            <img src="images/bookImg3.png">
        </div>
    </div>
    <div class="list">
        <div class="dateBox">
            <img src="images/littlePoint.png">
            <p class="date">12月11日</p>
        </div>
        <div class="listContent">
            <p>昨天圣诞节表演小飞很出色哦！</p>
            <img src="images/bookImg1.png">
        </div>
    </div>
    <div class="list">
        <div class="dateBox">
            <img src="images/littlePoint.png">
            <p class="date">11月11日</p>
        </div>
        <div class="listContent">
            <p>昨天圣诞节表演小飞很出色哦！</p>
            <img src="images/bookImg1.png">
            <img src="images/bookImg2.png">
            <img src="images/bookImg3.png">
        </div>--%>
    </div>
    <div class="weui-infinite-scroll" style="display: none">
        <div class="infinite-preloader"></div>
        正在加载
    </div>

    <!--//内容滚动区域底部留白-->
    <div class="marginBottom"></div>
</div>
<div class="header">
    <a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-arrow-left"></span></a>
    成长纪念册
</div>
<div class="bottom">
    <%--底部--%>
</div>
</body>
<script type="text/javascript">
    var basePath ="${pageContext.request.contextPath}/";
</script>

<script src="${pageContext.request.contextPath}/rs/js/wei/book.js"></script>
</html>