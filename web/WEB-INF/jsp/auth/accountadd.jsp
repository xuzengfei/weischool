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
	<form action="${ pageContext.request.contextPath }/auth/account/${empty account.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${account.id }">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>帐号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入帐号" id="user-name" value="${account.account }" name="account"  ${empty account.account?"":"disabled" }  datatype="*2-16" nullmsg="帐号不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入真实姓名" value="${account.user.name }" id="usename" name="user.name" datatype="*2-16" nullmsg="姓名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<c:if test="${ empty account }">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
			<div class="formControls col-5">
				<input type="password" id="password" name="password" placeholder="密码" autocomplete="off" value="" class="input-text" datatype="*6-20" nullmsg="密码不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="确认新密码" autocomplete="off" class="input-text" errormsg="您两次输入的新密码不一致！" datatype="*" nullmsg="请再输入一次新密码！" recheck="password" id="newpassword2" name="newpassword2">
			</div>
			<div class="col-4"> </div>
		</div>
	  </c:if>
		<div class="row cl">
			<label class="form-label col-3">角色：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" id="role" name="sysRole.id" size="1" >
				<c:if test="${not empty account.id}">
				<option >[${account.sysRole.name }]</option>
				</c:if>
				</select>
				</span> </div>
		</div>
		 <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>启用/禁用：</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" id="sex-1" name="isenable"  ${account.isenable eq 1?"checked":""} value=1 datatype="*"   nullmsg="请选择启用/禁用！">
					<label for="sex-1">启用</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex-2" name="isenable"  ${account.isenable eq 0?"checked":""} value=0 >
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
$(function(){
	 /***获得角色下拉列表***/
	$.getJSON(basePath+"auth/role/get/all",function(res){
			if(res.success){
				$.each(res.obj,function(i,item){
					$("#role").append("<option value=\""+item.id+"\">"+item.name+"</option>");
				})
			}
		})
})

</script>
</body>
</html>