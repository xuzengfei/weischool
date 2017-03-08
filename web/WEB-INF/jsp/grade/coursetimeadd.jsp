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
 
<t:base type="H-ui.css,icon.css,style.css,jquery,layer,valiform,H-ui,tools,wdate"></t:base>
 
<title></title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/course/add/time" method="post" class="form form-horizontal" id="form-admin-add">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>开始时间：</label>
			<div class="formControls col-5">
			<input type="text" name='start' id='start'  onfocus="WdatePicker({dateFmt:'HH:mm'})"
	 
			class="input-text Wdate" style="width:150px;" datatype="*2-20" nullmsg="请选择开始时间" placeholder="选择开始时间" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>结束时间：</label>
			<div class="formControls col-5">
			<input type="text" name='end' id='end'  onfocus="WdatePicker({dateFmt:'HH:mm'})"
	 
			class="input-text Wdate" style="width:150px;" datatype="*2-20" nullmsg="情选择结束时间" placeholder="选择结束时间" >
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
$(function(){
	$("#form-admin-add").Validform({
		tiptype:2,
		ajaxPost:true,
		beforeSubmit:function(curform){
			 //TODO提交之前，处理事件
		},
		callback:function(response){
			if(response.success){
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				parent.loadTime();//刷新父页面的数据列表
				parent.layer.close(index);
			}else{
				parent.layer.msg(response.msg);
			}
			$("#Validform_msg").css("display","none");
		}
	});
});
 
</script>
</body>
</html>