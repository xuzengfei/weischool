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
<title>缴费</title>
</head>
<body>
<table class="table table-border table-bordered radius">
	<tbody>
	<tr><th  class="text-r">学生名：</th><td>${name }</td><th  class="text-r">身份证：</th><td>${idNo }</td></tr>
	</tbody>
</table>

<t:datagrid name="role" actionUrl="${ pageContext.request.contextPath }/web/manager/cons/datagrid/${stId}" queryMode="group">
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="缴费项目"    field="project" query="true" ></t:dgCol>
	<t:dgCol title="缴费时间" width="100" field="payTime"  formatter="yyyy-MM-dd HH:mm" query="true" ></t:dgCol>
	<t:dgCol title="缴费金额"   field="amount" ></t:dgCol>
	<t:dgCol title="课时"   field="classNum" width="100"></t:dgCol>
	<t:dgCol title="缴费方式" field="payType" width="100"></t:dgCol>
	<t:dgCol title="券描述" field="remark" width="100"></t:dgCol>
	<t:dgCol title="操作人员" field="createName"></t:dgCol>
	<t:dgCol title="创建时间" field="createName" formatter="yyyy-MM-dd HH:mm"  ></t:dgCol>
	<t:dgCol title="操作" field="opt" width="200"></t:dgCol>
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/expe/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
<%--	<t:dgFunOpt funname="openWin('${ pageContext.request.contextPath }/web/manager/expe/to/edit/{id}','更新',500,450)" title="更新" icon="edit" ></t:dgFunOpt>--%>
	<t:dgToolBar icon="add" title="缴费" btClass="btn-primary" funname="openWin('${ pageContext.request.contextPath }/web/manager/cons/to/add/${stId}','缴费',700,550)"></t:dgToolBar>
</t:datagrid>
</body>
</html>