<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>玲珑艺术培训中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rs/css/wei/css/login.css">
    <script src="${pageContext.request.contextPath}/rs/js/wei/fastclick.js"></script>
    <script src="${pageContext.request.contextPath}/rs/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/rs/lib/layer_mobile/layer.js"></script>
</head>
<body>
<img src="${pageContext.request.contextPath}/rs/css/wei/images/loginImg.png" class="img-circle">
<form  id="loginform" >
    <input type="hidden" value="${openId}" name="openId">
    <select name="cpId" id="cpId">
        <option value="">--请选择校区--</option>
    </select>
    <input placeholder="请输入登录学号" style="border-bottom: none;" name="stNo">
 <%--   <input placeholder="请输入密码">
    <a>忘记密码</a>--%>
    <button type="button" onclick="login()">进&nbsp&nbsp&nbsp入</button>
</form>
</body>
<script type="text/javascript">
  var basePath ="${pageContext.request.contextPath}/";
</script>
<script src="${pageContext.request.contextPath}/rs/js/wei/login.js"></script>
</html>