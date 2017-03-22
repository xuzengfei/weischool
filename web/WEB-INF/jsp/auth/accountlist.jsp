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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户帐号 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<t:datagrid name="account" actionUrl="${ pageContext.request.contextPath }/auth/account/datagrid" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
    <t:dgCol title="帐号"   field="account"  query="true"></t:dgCol>
	<t:dgCol title="姓名" icon="maincolor" funname="show({sex})" field="user.name"  query="true"></t:dgCol>
	<t:dgCol title="长号" field="user.phone1" query="true" ></t:dgCol>
	<t:dgCol title="短号" field="user.phone2"></t:dgCol>
	<t:dgCol title="邮箱" field="user.email"></t:dgCol>
	<t:dgCol title="角色" field="sysRole.id" query="true" replace="${roleStr }"></t:dgCol>
	<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd" query="true"></t:dgCol>
	<t:dgCol title="状态"   field="isenable" replace="1_启用中,0_禁用中"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="200"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/auth/account/isenable/{id}/0" icon="disabled" title="禁用" exp="isenable#eq#1"  post="PUT" message="是否禁用？"></t:dgConfirmOpt>
    <t:dgConfirmOpt url="${ pageContext.request.contextPath }/auth/account/isenable/{id}/1" icon="enabled" title="启用" exp="isenable#eq#0"   post="PUT" message="是否启用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/auth/account/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgFunOpt funname="openWin('${ pageContext.request.contextPath }/auth/account/to/edit/{id}','更新',500,450)" title="更新" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="添加帐号" btClass="btn-primary" funname="openWin('${ pageContext.request.contextPath }/auth/account/to/add','添加帐号',500,450)"></t:dgToolBar>
	<t:dgToolBar icon="del" title="删除帐号" btClass="btn-danger" funname="add(id)"></t:dgToolBar>
</t:datagrid>
 
 
</body>
</html>