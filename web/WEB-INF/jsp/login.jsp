<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/html5.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/respond.min.js"></script>
<![endif]-->
<link href=" ${ pageContext.request.contextPath }/rs/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/rs/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/rs/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/rs/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台登录 -玲珑舞艺系统</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="index.html" method="post">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="account" name="" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="password" name="" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" id="validateCode" type="text" placeholder="验证码"  value="" style="width:150px;">
          <img   id="kaptchaImage" src="${ pageContext.request.contextPath }/auth/captcha" > <a id="kanbuq" href="javascript:changeCode();">看不清，换一张</a> </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="rmbPwd" value="">
            使我保持登录状态</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input name="" type="button" onclick="Login()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 广东药科大学BUG研究所</div>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/js/H-ui.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/security/security.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/js/login/login.js"></script>
</body>
<script type="text/javascript">
var basePath="${pageContext.request.contextPath}/";
</script>
</html>
