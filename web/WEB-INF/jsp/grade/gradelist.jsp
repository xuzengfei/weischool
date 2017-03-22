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
<t:datagrid name="grade" actionUrl="${ pageContext.request.contextPath }/web/manager/grade/datagrid/${cpId}" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="所属校区"  width="200"   field="campus.name" ></t:dgCol>
	<t:dgCol title="名称"  width="200"   field="name"  query="true"></t:dgCol>
	<t:dgCol title="任课老师"  width="200"   field="tcName"  align="left" query="true"></t:dgCol>
	<t:dgCol title="创建时间"  width="100"  field="createTime" formatter="yyyy-MM-dd" query="true"></t:dgCol>
	<t:dgCol title="状态" width="100"  field="isenable" replace="1_启用中,0_禁用中"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="300"></t:dgCol>
	<t:dgFunOpt funname="parent.openWinMax('${ pageContext.request.contextPath }/web/manager/grade/costtpl/{id}?graName={name}','课程费用模板')" title="课程费用模板" icon="&#xe636;" ></t:dgFunOpt>
	<t:dgFunOpt funname="parent.openWinMax('${ pageContext.request.contextPath }/web/manager/course/{id}?graName={name}','班级课表')" title="班级课表" icon="&#xe627;" ></t:dgFunOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/student/grade/${cpId}/{id}?graName={name}&teacher={tcName}','更新')" title="班级学生" icon="&#xe62b;" ></t:dgFunOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/grade/isenable/{id}/0" icon="disabled" title="禁用" exp="isenable#eq#1"  post="PUT" message="是否禁用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/grade/isenable/{id}/1" icon="enabled" title="启用" exp="isenable#eq#0"   post="PUT" message="是否启用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/grade/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/grade/to/edit/${cpId}/{id}')" title="更新" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="添加班级" btClass="btn-primary" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/grade/to/add/${cpId}','添加班级')"></t:dgToolBar>
</t:datagrid>
</body>
<script type="text/javascript">
/* function toStudent(url){
	window.location.href=url;
} */
</script>
</html>



