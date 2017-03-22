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
 
<t:base type="H-ui.css,check.css,icon.css,style.css,jquery,layer,check,valiform,H-ui,tools"></t:base>
 
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/auth/module/${empty module.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${module.id }">
		<input type="hidden" name="pid" value="${pid }">
		<input type="hidden" name="floor" value="${floor }">
		<input type="hidden" name="isAdmin" value="${empty module.isAdmin?0:module.isAdmin}">
		<div class="row cl">
			<label class="form-label col-3">所属栏目：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  disabled="disabled" value="${pname }"  >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>栏目名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入栏目名称"  value="${module.name }" name="name"    datatype="*2-10" nullmsg="栏目不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		  <div class="row cl">
			<label class="form-label col-3">跳转地址：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入跳转地址"  value="${module.url }" name="url"    datatype="*0-100"  >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">栏目图标：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入栏目图标"  value="${module.icon }" name="icon"    datatype="*0-20"  >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>排序号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入排序号"  value="${module.orderby }" name="orderby"    datatype="n1-10" nullmsg="排序好不能为空" >
			</div>
			<div class="col-4"> </div>
		</div>
		 <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>启用/禁用：</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" id="sex-1" name="isenable"  ${module.isenable eq 1?"checked":""} value=1 datatype="*"   nullmsg="请选择启用/禁用！">
					<label for="sex-1">启用</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex-2" name="isenable"  ${module.isenable eq 0?"checked":""} value=0 >
					<label for="sex-2">禁用</label>
				</div>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
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
			return true;
		},
		callback:function(response){
			if(response.success){
				parent.loadTree();
				window.location.reload();
			}else{
				$.Hidemsg();
				layer.msg(response.msg, {shift: 6});
			}


		}
	});
});
 
</script>
</body>
</html>