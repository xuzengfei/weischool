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
    <t:base type="H-ui.css,icon.css,style.css,jquery,layer,valiform,H-ui,tools,wdate,usertools"></t:base>
    <title>添加优惠/代金券</title>
</head>
<body>
<div class="pd-20">
    <form action="${ pageContext.request.contextPath }/web/manager/coupon/${empty obj.id?"add":"edit" }" method="post"
          class="form form-horizontal" id="form-admin-add">
        <input type="hidden" name="id" value="${obj.id }">
        <input type="hidden" name="st" value="${obj.st }">
        <input type="hidden" name="ed" value="${obj.ed }">

        <div class="row cl">
                <label class="form-label col-3"><span class="c-red">*</span>编号：</label>
                <div class="formControls col-5">
                    <input type="text" class="input-text" placeholder="输入编号"   value="${obj.no }"
                           name="no"   datatype="n5-16" nullmsg="编号不能为空" errormsg="请输入5-16位的数字" >
                </div>
                <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>代金金额：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text"  placeholder="输入代金金额"    name="useRule"   value="${obj.useRule}" datatype="n2-8" nullmsg="代金金额不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">开始时间：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text wdate" placeholder="选择开始时间"
                       value='<t:date value="${obj.st}"   pattern="yyyy-MM-dd"/>'
                       onclick="WdatePicker({onpicked:function(){
					$('input[name=st]').val(strTotimestamp($(this).val()));
					   }})" datatype="*2-20" nullmsg="请选择开始时间">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">结束时间：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text wdate" placeholder="选择结束时间"
                       value='<t:date value="${obj.ed}"   pattern="yyyy-MM-dd"/>'
                       onclick="WdatePicker({onpicked:function(){
					$('input[name=ed]').val(strTotimestamp($(this).val()));
					   }})" datatype="*2-20" nullmsg="请选择结束时间">
            </div>
            <div class="col-4"></div>
        </div>
 <c:if test="${empty obj.id }">
        <c:if test="${stId eq 'more' }">
                <div class="row cl">
                    <label class="form-label col-3">学生：</label>
                    <div class="formControls col-5">
                        <textarea name="stNames" id="stNames" cols="" rows="" onclick="checkboxSSelect('stIds','stNames')" class="textarea" placeholder="点击选择学生" dragonfly="true"></textarea>
                        <%--隐藏学生ID--%>
                        <input type="hidden" id="stIds" name="stIds">
                    </div>
                    <div class="col-4"></div>
                </div>
        </c:if>
        <c:if test="${stId ne 'more' }">
                  <input type="hidden" name="student.id" value="${stId }" >
        </c:if>
 </c:if>
        <div class="row cl">
            <label class="form-label col-3">使用说明：</label>
            <div class="formControls col-5">
                <textarea name="remark" cols="" rows="" class="textarea" placeholder="说点什么...50个字符以内" datatype="*0-50"
                          errormsg="请输入少于50个字符以内的描述" dragonfly="true"
                          onKeyUp="textarealength(this,50)">${obj.remark }</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/50</p>
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