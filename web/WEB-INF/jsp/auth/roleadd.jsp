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
	<form action="${ pageContext.request.contextPath }/auth/role/${empty role.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="roleId" value="${role.id }">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入角色名称"  value="${role.name }" name="name"    datatype="*2-16" nullmsg="角色不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		 <div class="row cl">
			<label class="form-label col-3">描述：</label>
			<div class="formControls col-5">
				<textarea name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...50个字符以内"  datatype="*0-50" errormsg="请输入少于50个字符以内的描述" dragonfly="true" onKeyUp="textarealength(this,50)">${role.remark }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">网站角色：</label>
			<div class="formControls col-5">
				<dl class="permission-list">
				<dd >
				</dd>
			</dl>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>启用/禁用：</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" id="sex-1" name="isenable"  ${role.isenable eq 1?"checked":""} value=1 datatype="*"   nullmsg="请选择启用/禁用！">
					<label for="sex-1">启用</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex-2" name="isenable"  ${role.isenable eq 0?"checked":""} value=0 >
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
		},
		callback:function(response){
			reloadDatagrid(response);
		}
	});


     $.getJSON("${ pageContext.request.contextPath }/auth/module/ignore/sys/module",function (res) {
		if(res.success){
			var data = res.obj;
			var data1=data;
			var html="";
			$.each(data,function (i,item) {
			    if(item.floor==1){
                    html+='<dl class="cl permission-list2">';
                    html+='<dt>';
                    html+='<label class="c-orange"><input type="checkbox"  name="modules" id="'+item.id+'" value="'+item.id+'"  > '+item.name+' </label>';
                    html+=' </dt>';
                    html+=' <dd>';
						$.each(data,function (i1,item1) {
						    if(item1.floor==2&&item1.pid==item.id)
                            html+='<label ><input type="checkbox"  pid="'+item.id+'" name="modules" id="'+item1.id+'"  value="'+item1.id+'"  > '+item1.name+' </label>';
						});
                    html+='</dd>';
                    html+='</dl>';
				}
            })
			$(".permission-list").children().html(html);

            $(".permission-list dt input:checkbox").click(function(){
                $(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
            });
            $(".permission-list2 dd input:checkbox").click(function(){
                if($(this).prop("checked")){
                    $("#"+$(this).attr("pid")).prop("checked",true);
                } else{
					if($("input[name="+$(this).attr("name")+"]:checked").length==0)
                        $("#"+$(this).attr("pid")).prop("checked",false);
                }
            });
			if($("#roleId").val()!=""){
                $.getJSON("${ pageContext.request.contextPath }/auth/rmodule/role/"+$("#roleId").val(),function (res) {
					if(res.success){
					    $.each(res.obj,function (i,item) {
					        $("#"+item.module).prop("checked",true);
                        })
					}
                })
			}
        }
    })
});

</script>

</body>
</html>