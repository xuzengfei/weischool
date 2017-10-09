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
 
<t:base type="H-ui.css,check.css,icon.css,style.css,jquery,layer,check,valiform,H-ui,tools,wdate,usertools"></t:base>
 
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/student/grade/${empty obj.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${obj.id }">
		<input type="hidden" name="campus.id" value="${cpId }">
		<input type="hidden" name="student.id" value="${obj.student.id }">
		<input type="hidden"  name="signTime" value="${obj.signTime }">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>学号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入学号"  value="${obj.student.no }" name="student.no"  id="stNo" onclick="radioSSelect('stNo')" readonly      datatype="*2-16" nullmsg="学号不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">所属专业：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入所属专业"  value="${obj.major }" name="major"    datatype="*0-20" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">课程顾问：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入课程顾问"  value="${obj.adviser }" name="adviser"    datatype="*0-16" >
			</div>
			<div class="col-4"> </div>
		</div>

		<div class="row cl">
			<label class="form-label col-3">报名时间：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text wdate"  placeholder="选择报名时间"  value='<t:date value="${obj.signTime}"   pattern="yyyy-MM-dd"/>'
					   onclick="WdatePicker({onpicked:function(){
					$('input[name=signTime]').val(strTotimestamp($(this).val()));
					   }})"   datatype="*0-16" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">所属班级：</label>
			<div class="formControls col-5">
				<c:if test="${gradId ne '-1'}">
					<input type="text" disabled value="${gradName}"  class="input-text wdate"   >
					<input type="hidden" value="${gradId}" name="grade.id">
				</c:if>
				<c:if test="${gradId eq '-1'}">
				<span class="select-box">
							<select class="select" size="1" name="grade.id"  datatype="*1-50"  nullmsg="请选择班级" >
								<option value="" >--请选择班级--</option>
								<c:forEach var="gd" items="${gradelist}">
									<option value="${gd.id}" ${obj.grade.id eq gd.id?"selected":""}>${gd.name}</option>
								</c:forEach>
							</select>
				</span>
				</c:if>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">剩余课时(节)：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入剩余课时"  value="${obj.restClass }" name="restClass"    datatype="n1-10" nullmsg="剩余课时不能为空" errormsg="请填写整数">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">描述：</label>
			<div class="formControls col-5">
				<textarea name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...200个字符以内"  datatype="*0-200" errormsg="请输入少于200个字符以内的描述" dragonfly="true" onKeyUp="textarealength(this,200)">${obj.remark }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
		<label class="form-label col-3"><span class="c-red">*</span>启用/禁用：</label>
		<div class="formControls col-5 skin-minimal">
			<div class="radio-box">
				<input type="radio" id="isenale-1" name="isenable"  ${obj.isenable eq 1?"checked":""} value=1 datatype="*"   nullmsg="请选择启用/禁用！">
				<label for="isenale-1">启用</label>
			</div>
			<div class="radio-box">
				<input type="radio" id="isenale-2" name="isenable"  ${obj.isenable eq 0?"checked":""} value=0 >
				<label for="isenale-2">禁用</label>
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
    var basePath = '${ pageContext.request.contextPath }/';
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