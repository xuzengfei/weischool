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
 
<t:base type="H-ui.css,check.css,icon.css,style.css,webuploader.css,jquery,layer,check,valiform,H-ui,tools,wdate,webuploader"></t:base>
 
<title>添加用户</title>
</head>
<body>
<table class="table table-border table-bordered table-striped radius">
	<thead class="text-c">
	<tr>
		<th colspan="5">学生基本信息</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td rowspan="7" style="width: 100px">
			<table class="mytable">
				<tr>
					<td><img  id="uploadFile"  src="${ pageContext.request.contextPath }/rs/images/user.jpg" alt="140x140" class="avatar size-MINI radius" style="width: 100px; height: 100px;"></td>
					<%--隐藏文件--%>
					<div id="filePicker"  style="display:none;" ></div>
				</tr>
				<tr>
					<td><input class="btn radius btn-secondary" type="button" value="更换头像"  id="uploadFile1"  ></td>
				</tr>
				<tr>
					<td><input class="btn radius btn-secondary" type="button" onclick="parent.openWinMax('${ pageContext.request.contextPath }/web/manager/student/grade/to/edit/${cpId}/${obj.id}','更新')" value="修改资料"></td>
				</tr>
				<tr>
					<td><input class="btn radius btn-secondary" type="button" onclick="parent.openWinMax('${ pageContext.request.contextPath }/web/manager/cert?stId=${obj.student.id}')" value="荣誉证书"></td>
				</tr>
				<tr>
					<td><input class="btn radius btn-secondary" type="button" onclick="parent.openWinMax('${ pageContext.request.contextPath }/web/manager/expe?stId=${obj.student.id}')"  value="成长经历"></td>
				</tr>
				<tr>
					<td><input class="btn radius btn-secondary" onclick="parent.openWinMax('${ pageContext.request.contextPath }/web/manager/cons?cpId=${cpId}&stId=${obj.id}&name=${obj.student.name}&idNo=${obj.student.idNo}')" type="button" value="费用缴纳"></td>
				</tr>
			</table>
		</td>
		<th class="text-r">学号：</th>
		<td>${obj.student.no}</td>
		<th class="text-r">姓名：</th>
		<td>${obj.student.name}</td>
	</tr>
	<tr>

		<th class="text-r">性别：</th>
		<td>${obj.student.sex}</td>
		<th class="text-r">身份证号：</th>
		<td>${obj.student.idNo}</td>
	</tr>

	<tr>

		<th class="text-r">联系人：</th>
		<td>${obj.student.contacts}</td>
		<th class="text-r">联系方式：</th>
		<td>${obj.student.contactsTel}</td>
	</tr>
	<tr>

		<th class="text-r">微信号：</th>
		<td>${obj.student.weiNo}</td>
		<th class="text-r">课程顾问：</th>
		<td>${obj.adviser}</td>
	</tr>
	<tr>

		<th class="text-r">报名时间：</th>
		<td><t:date value="${obj.signTime}"   pattern="yyyy-MM-dd"/></td>
		<th class="text-r">剩余课时：</th>
		<td>${obj.restClass}</td>
	</tr>
	<tr>
		<th class="text-r">所属校区：</th>
		<td>${obj.campus.name}</td>
		<th class="text-r">所属班级：</th>
		<td>${obj.grade.name}</td>
	</tr>
	<tr style="height: auto">
		<th class="text-r">备注：</th>
		<td colspan="3">${obj.remark}</td>
	</tr>
	</tbody>
</table>
<script src="${ pageContext.request.contextPath }/rs/js/ws/pic.js"></script>
<script type="text/javascript" >
var uploadcontroller = '${ pageContext.request.contextPath }/web/manager/attach/student/${obj.student.id}/1/1';
var basePath = '${ pageContext.request.contextPath }/';
var moduleId ="${obj.student.id}";

	$(function(){
        $("#uploadFile1").click(function () {
            $(".webuploader-element-invisible").trigger("click");
        })
		$("th").css("width","100px");
		$(".mytable td").css("border-left","0").addClass("text-c");
	})
</script>
</body>
</html>