
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
    <tr><th  class="text-r">班级名称：</th><td>${gradeName }</td></tr>
    </tbody>
</table>
<t:datagrid name="student" actionUrl="${ pageContext.request.contextPath }/web/manager/grade/costtpl/datagrid?gradeId=${gradeId }" queryMode="group">
    <t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
    <t:dgCol title="课时"  width="80"  align="right"  field="classNum"></t:dgCol>
    <t:dgCol title="费用(元)"  width="80"  align="right"   field="amount"></t:dgCol>
    <t:dgCol title="简述"  align="left"    field="remark" ></t:dgCol>
    <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
    <t:dgFunOpt funname="openWinMax('${ pageContext.request.contextPath }/web/manager/grade/costtpl/to/edit/${gradeId}/{id}','更新模板')" title="更新模板" icon="edit" ></t:dgFunOpt>
    <t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/grade/costtpl/del/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
    <t:dgToolBar icon="add" title="添加模板" btClass="btn-primary" funname="openWin('${ pageContext.request.contextPath }/web/manager/grade/costtpl/to/add/${gradeId}','添加模板',450,400)"></t:dgToolBar>
</t:datagrid>
</body>

</html>