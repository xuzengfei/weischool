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
 
<t:base type="H-ui.css,check.css,icon.css,style.css,jquery,layer,valiform,H-ui,tools,usertools"></t:base>
 
<title>添加模板</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/grade/costtpl/${empty obj.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${obj.id }">
		<input type="hidden" name="gradeId" value="${gradeId}">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>课时：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入课时,只能为整数"  value="${obj.classNum }" name="classNum"    datatype="n1-10" nullmsg="课时不能为空"  errormsg="请填写整数">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>费用：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入费用,只能为整数"  value="${obj.amount }" name="amount"    datatype="n1-10" nullmsg="费用不能为空"  errormsg="请填写整数">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>简述：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入简述,32字内"  value="${obj.remark }" name="remark"    datatype="*1-32" nullmsg="简述不能为空"  >
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