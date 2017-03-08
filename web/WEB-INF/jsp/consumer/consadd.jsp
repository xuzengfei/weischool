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
 
<t:base type="H-ui.css,check.css,icon.css,style.css,jquery,layer,check,valiform,H-ui,tools,wdate"></t:base>
 
<title>缴费</title>
</head>
<body>
<div class="pd-20">
	<form action="${ pageContext.request.contextPath }/web/manager/cons/add" method="post" class="form form-horizontal" id="form-admin-add">
	<input type="hidden"   value="${stId }" name="student.id"   >
	<input type="hidden" name='payTime' id='payTime'   >
	<div class="row cl">
			<label class="form-label col-3">项目类型：</label>
			<div class="formControls col-5"> <span class="select-box" >
				<select class="select"   name="projectType"  id="projectType"  datatype="*1-50" nullmsg="缴费项目不能为空"  >
					<option value="">--请选择项目类型--</option>
					 <option value="1"  >课时费</option>
					 <option value="2" >活动报名费</option>
					 <option value="3"> 其它费用</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>缴费项目：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入缴费项目"    name="project"    datatype="*2-50" nullmsg="缴费项目不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
		<label class="form-label col-3"><span class="c-red">*</span>金额（元）：</label>
		<div class="formControls col-5">
			<input type="text" class="input-text"  placeholder="输入金额"   name="amount"    datatype="n1-9" nullmsg="金额不能为空">
		</div>
		<div class="col-4"> </div>
		</div>
	 
		
	 
			<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>缴费方式：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="输入缴费方式"    name="payType"    datatype="*2-50" nullmsg="缴费方式不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>缴费时间：</label>
			<div class="formControls col-5">
			<input type="text" name='payTime1' id='payTime1'  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:function() {$dp.$('payTime').value=strTotimestamp($dp.$('payTime1').value)}})"
		<%-- 		<c:if test="${not empty obj.payTime }">
					<fmt:formatDate value="${obj.payTime}" pattern="yyyy-MM-dd HH:mm"/>
				</c:if> --%>
			class="input-text Wdate" style="width:150px;" datatype="*2-20" nullmsg="情选择缴费时间" placeholder="选择缴费时间" >
			</div>
			<div class="col-4"> </div>
		</div>	
		<div class="row cl">
			<label class="form-label col-3">使用劵：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select"   name="coupId"   >
					<option value="">--请选择使用劵--</option>
					<c:forEach var="coup" items="${coups}">
						<option value="${coup.id}">代金券【${coup.useRule}】元</option>
					</c:forEach>
				</select>
				</span>
			</div>
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
	$("#projectType").change( function () { 
			if($(this).val()=="1"){
				$(this).parent().parent().parent().after("<div id=\"classNum_div\" class=\"row cl\">"+
				"<label class=\"form-label col-3\"><span class=\"c-red\">*</span>课时（节）：</label>"+
				"<div class=\"formControls col-5\">"+
					"<input type=\"text\" class=\"input-text\"  placeholder=\"输入课时\"     name=\"classNum\"    datatype=\"n1-9\" nullmsg=\"课时不能为空\">"+
				"</div>"+
				"<div class=\"col-4\"> </div>"+
			"</div>");
			}else{
				$("#classNum_div").remove();
			}
	} );
});
 
</script>
</body>
</html>