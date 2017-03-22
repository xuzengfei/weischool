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

    <title>添加教师</title>
</head>
<body>
<div class="pd-20">
    <form action="${ pageContext.request.contextPath }/web/manager/teacher/${empty obj.id?"add":"edit" }" method="post" class="form form-horizontal" id="form-admin-add">
        <input type="hidden" name="id" value="${obj.id }">
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>工号：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text"  placeholder="输入工号"  value="${obj.emNo}" name="emNo"    datatype="*2-16" nullmsg="工号不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text"  placeholder="输入姓名"  value="${obj.name }" name="name"    datatype="*2-16" nullmsg="姓名不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>性别：</label>
            <div class="formControls col-5 skin-minimal">
                <div class="radio-box">
                    <input type="radio" id="sex-1" name="sex"  ${obj.sex eq "男"?"checked":""} value="男" datatype="*"   nullmsg="请选择性别！">
                    <label for="sex-1">男</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="sex-2" name="sex"  ${obj.sex eq "女"?"checked":""} value="女" >
                    <label for="sex-2">女</label>
                </div>
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">身份证号：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text"  placeholder="输入身份证号"  value="${obj.idNo }" name="idNo"    datatype="*0-50" >
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>电话：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text"  placeholder="输入电话"  value="${obj.tel}" name="tel"    datatype="*2-16" nullmsg="电话人不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">微信号：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text"  placeholder="输入微信号"  value="${obj.weiNo }" name="weiNo"   datatype="*0-50" >
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