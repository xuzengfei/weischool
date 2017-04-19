<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/mytags.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/html5.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/respond.min.js"></script>
<![endif]-->

<link href="${ pageContext.request.contextPath }/rs/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/rs/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/rs/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="${ pageContext.request.contextPath }/rs/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/rs/css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
<title>玲珑舞艺系统 v1.0</title>
</head>
<body>
<header class="Hui-header cl"> <a class="Hui-logo l" title="BUG研究所OA" href="/">玲珑艺术教育管理系统</a> <a class="Hui-logo-m l" href="/" title="V1.0"></a> <span class="Hui-subtitle l">V1.0</span>
	
	<nav class="mainnav cl" id="Hui-nav">
		<ul>
			<li class="dropDown dropDown_click"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 快速工作 <i class="Hui-iconfont">&#xe6d5;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<c:forEach var="st" items="${shortcuts}">
						<li><a href="javascript:;" onclick="addTab('${st.url}')"><i class="Hui-iconfont">${st.icon}</i> ${st.name}</a></li>
					</c:forEach>
				</ul>
			</li>
		</ul>
	</nav>
	<ul class="Hui-userbar">
		<li>${user.sysRole.name}</li>
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${user.user.name} <i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:addTab('auth/user/to/personal');">个人信息</a></li>
				<li><a href="javascript:addTab('auth/account/to/repassword');">修改密码</a></li>
				<li><a href="javascript:window.location.href='${ pageContext.request.contextPath }/auth/login/loginout'">退出</a></li>
			</ul>
		</li>
		<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe62f;</i></a> </li>
		<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
				<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
				<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
				<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
				<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
				<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
			</ul>
		</li>
	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>
<aside class="Hui-aside">

	<div class="menu_dropdown bk_2">
		<c:forEach var="m1" items="${menu1}">
			<dl id="menu-article">
				<dt>
					<c:if test="${not empty m1.icon}"><i class="Hui-iconfont">${m1.icon}</i> </c:if>
					${m1.name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<c:if test="${not empty menu2}">
				<dd>
					<ul>
						<c:forEach items="${menu2}" var="m2"  >
							<c:if test="${m2.pid eq m1.id}">
								<li><a _href="${m2.url}" href="javascript:void(0)">${m2.name}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</dd>
				</c:if>
			</dl>
		</c:forEach>
	</div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="${ pageContext.request.contextPath }/auth/login/home">我的桌面</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${ pageContext.request.contextPath }/auth/login/home"></iframe>
		</div>
	</div>
</section>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/js/H-ui.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/rs/js/H-ui.admin.js"></script> 
 <script type="text/javascript">

	   function addTab(_href){
     	$(".menu_dropdown a[_href='"+_href+"']").trigger("click");
	 }
</script>
</body>
</html>