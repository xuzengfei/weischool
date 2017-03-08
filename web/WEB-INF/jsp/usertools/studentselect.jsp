
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
	<title>学生选择框</title>
</head>
<body>
<t:datagrid name="student" actionUrl="${ pageContext.request.contextPath }/web/manager/utools/st/datagrid/isenable/1" queryMode="group">
	<t:dgCol title="学号"  field="no"  query="true"></t:dgCol>
	<t:dgCol title="姓名"  field="name"  query="true"></t:dgCol>
</t:datagrid>
<script type="text/javascript" >
	$(function(){
        $("table tbody").on("click","tr", function() {
            var no = $(this).children("td:nth-child(1)").html();
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.setNo(no);//刷新父页面的数据列表
            parent.layer.close(index);
        })
	})
</script>
</body>
</html>