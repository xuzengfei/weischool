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
    <t:base type="H-ui.css,icon.css,style.css,jquery,layer,valiform,H-ui,tools,usertools"></t:base>
    <title>添加证书</title>
</head>
<body>
<div class="pd-20">
    <form action="${ pageContext.request.contextPath }/web/manager/studentopenidvo/${id}" method="post"
          class="form form-horizontal" id="form-admin-add">
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">*</span>学生：</label>
                <div class="formControls col-5">
                    <textarea name="stNames" id="stNames" cols="" rows="" onclick="checkboxSSelect('stIds','stNames')" class="textarea" placeholder="点击选择学生" dragonfly="true"></textarea>
                        <%--隐藏学生ID--%>
                    <input type="hidden" id="stIds" name="stIds">
                </div>
                <div class="col-4"></div>
            </div>
        <div class="row cl">
            <div class="col-9 col-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>

<script type="text/javascript">
    var basePath = '${ pageContext.request.contextPath }/';
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