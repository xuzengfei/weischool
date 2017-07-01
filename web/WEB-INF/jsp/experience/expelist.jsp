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
<title>荣誉证书</title>
</head>
<body>
<c:if test="${empty stId}">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 学生管理 <span class="c-gray en">&gt;</span> 成长经历 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
</c:if>
<t:datagrid name="role" actionUrl="${ pageContext.request.contextPath }/web/manager/expe/datagrid?stId=${stId}" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<c:if test="${empty stId}">
	<t:dgCol title="学生" width="100" field="student.name"  query="true" ></t:dgCol>
	</c:if>
	<t:dgCol title="时间"  width="100"   field="expeTime"   formatter="yyyy-MM-dd" query="true"></t:dgCol>
	<t:dgCol title="活动经历"   field="remark" ></t:dgCol>
	<t:dgCol title="图片" width="50" image="true"    field="" icon="&#xe646;"  ></t:dgCol>
	<t:dgCol title="状态" width="100"  field="isenable" replace="1_启用中,0_禁用中"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="200"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/expe/isenable/{id}/0" icon="disabled" title="禁用" exp="isenable#eq#1"  post="PUT" message="是否禁用？"></t:dgConfirmOpt>
    <t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/expe/isenable/{id}/1" icon="enabled" title="启用" exp="isenable#eq#0"   post="PUT" message="是否启用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/expe/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/expe/to/edit/{id}','更新')" title="更新" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="添加经历" btClass="btn-primary" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/expe/to/add?stId=${stId}','添加经历')"></t:dgToolBar>
</t:datagrid>
</body>
</html>