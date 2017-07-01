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
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>券管理 <span class="c-gray en">&gt;</span> 代金券 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<t:datagrid name="campus" actionUrl="${ pageContext.request.contextPath }/web/manager/coupon/datagrid?student.id=${stId}" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="编号"     field="no" align="left" query="true"></t:dgCol>
	<t:dgCol title="代金金额" width="100"  field="useRule" align="right" ></t:dgCol>
	<t:dgCol title="学生" width="200"  field="student.name"  query="true" ></t:dgCol>
	<t:dgCol title="开始时间"  width="100" align="center"  field="st" formatter="yyyy-MM-dd" query="true"></t:dgCol>
	<t:dgCol title="截止时间"  width="100"  align="center" field="ed" formatter="yyyy-MM-dd"></t:dgCol>
	<t:dgCol title="状态" width="100"  field="isenable" replace="1_已使用,0_未使用"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="250"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/coupon/del/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgFunOpt exp="isenable#eq#0" funname="openWin('${ pageContext.request.contextPath }/web/manager/coupon/to/edit/{id}','更新',500,450)" title="更新" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="添加" btClass="btn-primary" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/coupon/to/add/${stId}','添加代金券')"></t:dgToolBar>
</t:datagrid>
</body>
</html>