<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <LINK rel="Bookmark" href="/favicon.ico">
    <LINK rel="Shortcut Icon" href="/favicon.ico"/>
    <t:base type="H-ui.css,check.css,icon.css,style.css,webuploader.css,jquery,layer,check,valiform,H-ui,tools,wdate,webuploader,usertools"></t:base>
    <title>添加成绩</title>
</head>
<body>
<div class="pd-20">
    <form action="${ pageContext.request.contextPath }/web/manager/exam/${empty obj.id?"add":"edit" }" method="post"
          class="form form-horizontal" id="form-admin-add">
        <input type="hidden" name="id" value="${obj.id }">
        <input type="hidden" name="exTime" value="${obj.exTime }">
        <c:if test="${empty obj.id }">
             <c:choose>
                 <c:when test="${empty stId}">
                     <div class="row cl">
                         <label class="form-label col-3">学生：</label>
                         <div class="formControls col-5">
                        <input  type="text" class="input-text" name="stName" id="stName" onclick="radioSSelect1('stId','stName')"  placeholder="点击选择学生"/>
                                 <%--隐藏学生ID--%>
                             <input type="hidden" id="stId" name="student.id">
                         </div>
                         <div class="col-4"></div>
                     </div>
                 </c:when>
                 <c:otherwise>
                     <input type="hidden" name="student.id" value="${stId }">
                 </c:otherwise>
             </c:choose>
        </c:if>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>标题：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" placeholder="输入标题" value="${ obj.title }" name="title" datatype="*2-16" nullmsg="标题不能为空">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">考试时间：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text wdate" placeholder="考试时间"
                       value='<t:date value="${obj.exTime}"   pattern="yyyy-MM-dd HH:mm"/>'
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:function(){
					$('input[name=exTime]').val(strTotimestamp($(this).val()));
					   }})" datatype="*2-20" nullmsg="请选择时间">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">描述：</label>
            <div class="formControls col-5">
                <textarea name="remark" cols="" rows="" class="textarea" placeholder="说点什么...50个字符以内" datatype="*0-50"
                          errormsg="请输入少于50个字符以内的描述" dragonfly="true"
                          onKeyUp="textarealength(this,50)">${obj.remark }</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
            </div>
            <div class="col-4"></div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>通过/不通过：</label>
            <div class="formControls col-5 skin-minimal">
                <div class="radio-box">
                    <input type="radio" id="sex-1" name="status"  ${obj.status eq 1 or empty obj.status?"checked":""}
                           value=1
                           datatype="*" nullmsg="请选择通过/不通过！">
                    <label for="sex-1">通过</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="sex-2" name="status"  ${obj.status eq 0?"checked":""} value=0>
                    <label for="sex-2">不通过</label>
                </div>
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <div class="col-9 col-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">

    var basePath = "${ pageContext.request.contextPath }/";
    var moduleId = "${obj.id}";
    $(function () {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
        $("#form-admin-add").Validform({
            tiptype: 2,
            ajaxPost: true,
            beforeSubmit: function (curform) {
                //TODO提交之前，处理事件
            },
            callback: function (response) {
                reloadDatagrid(response);
            }
        });
    });

</script>
</body>
</html>