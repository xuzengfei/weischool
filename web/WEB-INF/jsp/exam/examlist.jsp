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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 学生管理 <span class="c-gray en">&gt;</span> 学生成绩 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
</c:if>
<t:datagrid name="role" actionUrl="${ pageContext.request.contextPath }/web/manager/exam/datagrid?stdudent.id=${st.id}" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<c:if test="${empty stId}">
	<t:dgCol title="学生" width="100" field="student.name"  query="true" ></t:dgCol>
	</c:if>
	<t:dgCol title="标题"   field="title" ></t:dgCol>
	<t:dgCol title="考试时间"  width="150"   field="exTime"   formatter="yyyy-MM-dd HH:mm" query="true"></t:dgCol>
	<t:dgCol title="状态" width="100"  field="status" replace="1_通过,0_不通过"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="200"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/exam/status/{id}/0" icon="disabled" title="不通过" exp="status#eq#1"  post="PUT" message="确认不通过？"></t:dgConfirmOpt>
    <t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/exam/status/{id}/1" icon="enabled" title="通过" exp="status#eq#0"   post="PUT" message="确认通过？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/exam/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/exam/to/edit/{id}','更新')" title="更新" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="添加成绩" btClass="btn-primary" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/exam/to/add?stId=${stId}','添加成绩')"></t:dgToolBar>
</t:datagrid>
</body>
</html>