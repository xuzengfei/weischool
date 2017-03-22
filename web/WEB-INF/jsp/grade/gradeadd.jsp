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
 
<t:base type="H-ui.css,check.css,icon.css,style.css,jquery,layer,check,valiform,H-ui,tools,usertools"></t:base>
 
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/grade/${empty obj.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${obj.id }">
		<input type="hidden" name="campus.id" value="${cpId}">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>班级名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入班级名"  value="${obj.name }" name="name"    datatype="*2-16" nullmsg="班级名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>任课老师：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入任课老师"  value="${obj.tcName }" name="tcName"  id="tcName" readonly onclick="radioTSelect('teacher','tcName')"  datatype="*2-16" nullmsg="任课老师不能为空">
				<%--隐藏ID--%><input type="hidden" value="${obj.teacher }" id="teacher"  name="teacher">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
		<label class="form-label col-3"><span class="c-red">*</span>启用/禁用：</label>
		<div class="formControls col-5 skin-minimal">
			<div class="radio-box">
				<input type="radio" id="sex-1" name="isenable"  ${obj.isenable eq 1?"checked":""} value=1 datatype="*"   nullmsg="请选择启用/禁用！">
				<label for="sex-1">启用</label>
			</div>
			<div class="radio-box">
				<input type="radio" id="sex-2" name="isenable"  ${obj.isenable eq 0?"checked":""} value=0 >
				<label for="sex-2">禁用</label>
			</div>
		</div>
		<div class="col-4"> </div>
	</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<input class="btn btn-secondary radius" type="reset" value="&nbsp;&nbsp;重置&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
    var basePath ="${ pageContext.request.contextPath }/";
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#form-admin-add").Validform({
		tiptype:2,
		ajaxPost:true,
		beforeSubmit:function(curform){
			 //TODO提交之前，处理事件
		},
		callback:function(response){
			reloadDatagrid(response);
		}
	});
});
 
</script>
</body>
</html>