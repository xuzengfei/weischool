
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
	<title>校区学生</title>
</head>
<body>
<table class="table table-border table-bordered radius">
  <tbody>
    <tr><th  class="text-r">班级名称：</th><td>${graName }</td><th  class="text-r">任课教师：</th><td>${teacher }</td></tr>
  </tbody>
</table>
<t:datagrid name="student" actionUrl="${ pageContext.request.contextPath }/web/manager/student/grade/datagrid/${cpId}/${graId }" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="学生基本ID"   hidden="true"  field="student.id"></t:dgCol>
	<t:dgCol title="学号" icon="maincolor" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/student/grade/to/view/${cpId}/{id}')" field="student.no"  query="true"></t:dgCol>
	<t:dgCol title="姓名"  field="student.name"  query="true"></t:dgCol>
	<t:dgCol title="性别"  field="student.sex"></t:dgCol>
	<t:dgCol title="联系人"  field="student.contacts"></t:dgCol>
	<t:dgCol title="联系方式"  field="student.contactsTel"></t:dgCol>
	<t:dgCol title="微信号"     field="student.weiNo" ></t:dgCol>
	<t:dgCol title="剩余课时"     field="restClass" ></t:dgCol>
	<t:dgCol title="状态" width="100"  field="isenable" replace="1_启用中,0_禁用中"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="300"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/student/grade/isenable/{id}/0" icon="disabled" title="禁用" exp="isenable#eq#1"  post="PUT" message="是否禁用？"></t:dgConfirmOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/student/grade/isenable/{id}/1" icon="enabled" title="启用" exp="isenable#eq#0"   post="PUT" message="是否启用？"></t:dgConfirmOpt>
	<t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/coupon/{student.id}','学生代金券')" title="学生代金券" icon="edit" ></t:dgFunOpt>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/student/grade/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
	<t:dgToolBar icon="add" title="添加资料" btClass="btn-primary" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/student/grade/to/add/${cpId}/${graId}?graName=${graName}','添加资料')"></t:dgToolBar>
</t:datagrid>
</body>

</html>