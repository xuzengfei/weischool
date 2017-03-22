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
<t:base type="H-ui.css,check.css,icon.css,style.css,webuploader.css,jquery,layer,check,valiform,H-ui,tools,wdate,webuploader,usertools,editor"></t:base>
<title>新增/更新校园经历</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/cplife/${empty obj.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${obj.id }">
		<input type="hidden"  name="st" value="${obj.st }">
		<c:if test="${not empty cpId }">
		<input type="hidden"  name="cpId" value="${cpId }">
		</c:if>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入标题"  value="${obj.title }" name="title"    datatype="*2-100" nullmsg="标题不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
		<label class="form-label col-3">活动时间：</label>
		<div class="formControls col-5">
			<input type="text" class="input-text wdate"  placeholder="选择活动时间"  value='<t:date value="${obj.st}"   pattern="yyyy-MM-dd"/>'
				   onclick="WdatePicker({onpicked:function(){
					$('input[name=st]').val(strTotimestamp($(this).val()));
					   }})"   datatype="*2-20" nullmsg="请选择活动时间"  >
		</div>
		<div class="col-4"></div>
		</div>

		 <div class="row cl">
			<label class="form-label col-3">活动内容：</label>
			<div class="formControls col-5">
				 <t:editor id="remark"   datatype="*"   nullmsg="请填写活动内容！" value="${obj.remark}" ></t:editor>
			</div>
			<div class="col-4"> </div>
		</div>
<%--##############################图片上传开始#####################################--%>
		<div class="row cl">
			<label class="form-label col-3">标题图片</label>
			<div class="formControls col-5">
				<img id="uploadFile" src="${ pageContext.request.contextPath }/rs/images/webupload/image.png" alt="136x200" class="avatar size-MINI thumbnail" style="width: 200px; height: 136px;cursor: pointer">

			</div>
			<%--隐藏文件--%>
			<div id="filePicker"  style="display:none;" ></div>
			<div class="col-4"></div>
		</div>
<%--##############################图片上传结束#####################################--%>
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
			</div>
		</div>
	</form>
</div>
<script src="${ pageContext.request.contextPath }/rs/js/ws/pic.js"></script>
<script type="text/javascript">
	var basePath ="${ pageContext.request.contextPath }/";
	var moduleId ="${obj.id}";
    var uploadcontroller = '${ pageContext.request.contextPath }/web/manager/attach/cplife/${empty obj.id?-1:obj.id}/1/1';
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