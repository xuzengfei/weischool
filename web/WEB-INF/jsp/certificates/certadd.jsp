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
<t:base type="H-ui.css,check.css,icon.css,style.css,webuploader.css,jquery,layer,check,valiform,H-ui,tools,wdate,webuploader,usertools"></t:base>
<title>添加证书</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/cert/${empty obj.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${obj.id }">
		<input type="hidden"  name="cerTime" value="${obj.cerTime }">
		<c:if test="${empty obj.id}">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>学号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text"  placeholder="输入学号"  value="${empty obj.student.no?stNo:obj.student.no }" name="student.no"   id="stNo" onclick="radioSSelect('stNo')" readonly   datatype="*2-16" nullmsg="学号不能为空">
				</div>
				<div class="col-4"> </div>
			</div>
		</c:if>

		<div class="row cl">
		<label class="form-label col-3">时间：</label>
		<div class="formControls col-5">
			<input type="text" class="input-text wdate"  placeholder="选择时间"  value='<t:date value="${obj.cerTime}"   pattern="yyyy-MM-dd"/>'
				   onclick="WdatePicker({onpicked:function(){
					$('input[name=cerTime]').val(strTotimestamp($(this).val()));
					   }})"   datatype="*2-20" nullmsg="请选择时间"  >
		</div>
		<div class="col-4"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>证书编号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入证书编号"  value="${obj.no }" name="no"    datatype="*2-100" nullmsg="证书编号不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		 <div class="row cl">
			<label class="form-label col-3">证书项目：</label>
			<div class="formControls col-5">
				<textarea name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...50个字符以内"  datatype="*0-50" errormsg="请输入少于50个字符以内的描述" dragonfly="true" onKeyUp="textarealength(this,50)">${obj.remark }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
			</div>
			<div class="col-4"> </div>
		</div>
<%--##############################图片上传开始#####################################--%>
		<div class="row cl">
			<label class="form-label col-3">图片上传：</label>
			<div class="formControls col-5">
				<div class="portfolio-content">
					<ul class="cl portfolio-area">
						<li class="item uploadFile" >
							<div class="portfoliobox">
								<div class="picbox"><a href="javascript:void(0)" id="uploadFile" data-lightbox="gallery" data-title=" "><img src="${ pageContext.request.contextPath }/rs/images/icon-add.png"></a></div>
								<div class="textbox"> </div>
								<%--隐藏文件--%>
								<div id="filePicker"  style="display:none;" ></div>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-4"> </div>
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
<script src="${ pageContext.request.contextPath }/rs/js/ws/attach.js"></script>
<script type="text/javascript">
	var basePath ="${ pageContext.request.contextPath }/";
	var moduleId ="${obj.id}";
	var uploadcontroller = '${ pageContext.request.contextPath }/web/manager/attach/certificate/${empty obj.id?-1:obj.id}/1/2';
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