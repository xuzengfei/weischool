<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="/favicon.ico" >
	<LINK rel="Shortcut Icon" href="/favicon.ico" />
	<t:base type="H-ui.css,icon.css,jquery,layer,laypage,wdate,tools"></t:base>
	<title>学生微信绑定</title>
</head>
<body><nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 教师管理<span class="c-gray en">&gt;</span> 教师微信绑定   <a class="btn btn-success radius r mr-20"
																																							  style="line-height:1.6em;margin-top:3px"
																																							  href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>

<t:datagrid name="teaher" actionUrl="${ pageContext.request.contextPath }/web/manager/teacheropenidvo/datagrid" queryMode="group"  >
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="工号"  field="teacher.emNo"  query="true"></t:dgCol>
	<t:dgCol title="姓名"  field="teacher.name"  query="true"></t:dgCol>
	<t:dgCol title="绑定时间"  width="150"  field="ct" formatter="yyyy-MM-dd HH:mm" query="true"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/teacheropenidvo/{id}" message="确定解除绑定？" title="解绑"  post="DELETE" icon="del" ></t:dgConfirmOpt>
</t:datagrid>
</body>
</html>