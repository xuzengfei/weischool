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
<t:base type="H-ui.css,icon.css,style.css,jquery,layer,valiform,H-ui,tools,editor"></t:base>
<title>关于学校</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/about/sc/addorup" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id"  id="id" value="">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入标题"  value="${obj.title }" name="title" id="title"    datatype="*2-100" nullmsg="标题不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		 <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>描述：</label>
			<div class="formControls col-5">
				 <t:editor id="remark"   datatype="*"   nullmsg="请填写描述！" value="${obj.remark}" ></t:editor>
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
    $.getJSON(basePath + "web/manager/about/sc/get", function (res) {
        if (res.success) {
            var data = res.obj;
            if(data!=null){
				$("#remark").val(data.remark);
                $("#id").val(data.id);
                $("#title").val(data.title);
			}



        }
    });
	$("#form-admin-add").Validform({
		tiptype:2,
		ajaxPost:true,
		beforeSubmit:function(curform){
			 //TODO提交之前，处理事件
		},
		callback:function(response){
            parent.layer.msg(response.msg);
            window.location.reload();
		}
	});
});

</script>
</body>
</html>