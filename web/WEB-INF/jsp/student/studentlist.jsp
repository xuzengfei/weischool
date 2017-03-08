
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<title>学生基本信息</title>
</head>
<body><nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 学生管理<span class="c-gray en">&gt;</span> 基本信息   <a class="btn btn-success radius r mr-20"
																																							style="line-height:1.6em;margin-top:3px"
																																							href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>

<%--<t:datagrid name="student" actionUrl="${ pageContext.request.contextPath }/web/manager/student/datagrid/${cpId}" queryMode="group">--%>
<t:datagrid name="student" actionUrl="${ pageContext.request.contextPath }/web/manager/student/datagrid" queryMode="group"  >
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="学号"  field="no"  query="true"></t:dgCol>
	<t:dgCol title="姓名"  field="name"  query="true"></t:dgCol>
	<t:dgCol title="性别"  field="sex"></t:dgCol>
	<t:dgCol title="联系人"  field="contacts"></t:dgCol>
	<t:dgCol title="联系方式"  field="contactsTel"></t:dgCol>
	<t:dgCol title="微信号"     field="weiNo" ></t:dgCol>
	<t:dgCol title="状态" width="100"  field="isenable" replace="1_启用中,0_禁用中"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="250"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/student/isenable/{id}/0" icon="disabled" title="禁用" exp="isenable#eq#1"  post="PUT" message="是否禁用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/student/isenable/{id}/1" icon="enabled" title="启用" exp="isenable#eq#0"   post="PUT" message="是否启用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/student/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/student/to/edit/${cpId}/{id}','更新')" title="更新" icon="edit" ></t:dgFunOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/coupon/{id}','学生代金券')" title="学生代金券" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="添加学生" btClass="btn-primary" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/student/to/add/${cpId}','添加学生')"></t:dgToolBar>
</t:datagrid>
</body>
</html>