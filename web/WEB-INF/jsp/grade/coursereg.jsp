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
	<t:base type="H-ui.css,icon.css,jquery,layer,laypage,wdate,tools"></t:base>
	<title>学生上课签到</title>
</head>
<body>
<t:datagrid name="grade" actionUrl="${ pageContext.request.contextPath }/web/manager/gradereg/gtId/${gtId}" queryMode="group" pagination="false" >
	<t:dgCol title="ID"   field="id"  hidden="true"></t:dgCol>
	<t:dgCol title="stid"   field="stId"  hidden="true"></t:dgCol>
	<t:dgCol title="课程名称"  width="200"    field="gradeName" ></t:dgCol>
	<t:dgCol title="任课老师"  width="200"   field="tcName"  ></t:dgCol>
	<t:dgCol title="学生名称"  width="200"   field="stName"  align="left" query="true"></t:dgCol>
	<t:dgCol title="创建时间"  width="100"  field="ct" formatter="yyyy-MM-dd HH:mm" ></t:dgCol>
	<t:dgCol title="上课状态" width="100"  field="status"   query="true" replace="1_准时,2_请假,3_旷课,4_迟到"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
<%--
	<t:dgConfirmOpt url="${ pageContext.request.contextPath }/web/manager/grade/{id}" message="是否删除" title="删除"  post="DELETE" icon="del" ></t:dgConfirmOpt>
--%>
	<t:dgFunOpt funname="upd('{id}',{status},this)" title="更新状态" icon="edit" ></t:dgFunOpt>
	<t:dgToolBar icon="add" title="点名" funname="openWinMax('${ pageContext.request.contextPath }/web/manager/gradereg/gtId/${gtId}/to/sign')"  btClass="btn-primary"></t:dgToolBar>
</t:datagrid>
</body>
<script type="text/javascript">

	function getIds(){
	    var idArray =[];
	   $(".table").find(".text-c").each(function(i){
	        if(i>0){
                idArray[i-1]=$(this).children().eq(1).html();
			}
	   })
		return idArray;
	}
/* function toStudent(url){
	window.location.href=url;
} */
function upd(id,status,crtl){
    var selected1="";
    var selected2="";
    var selected3="";
    var selected4="";
    switch(status){
		case 1:selected1="selected";break;
		case 2:selected2="selected";break;
		case 3:selected3="selected";break;
		default:selected4="selected";
	}
    //自定页
    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        closeBtn: 0, //不显示关闭按钮
        anim: 2,
		title:"更新状态",
        btn: ['确定', '取消'],
        shadeClose: true, //开启遮罩关闭
        content: '<div class="text-c seachform" style="margin-top: 10px" >状态：<span class="select-box inline">' +
		'<select  id="nstatus" class="select  groupClass" style="width: 80px">' +
		'<option '+selected1+' value="1">准时</option>' +
		'<option '+selected2+' value="2">请假</option>' +
		'<option '+selected3+' value="3">旷课</option>' +
		'<option '+selected4+' value="4">迟到</option>' +
		'</select></span></div>',
        yes: function(index){
            var nstatus =$("#nstatus").val();
            if(status!=nstatus){
             	$.post("${ pageContext.request.contextPath }/web/manager/gradereg/"+id+"/status/"+nstatus,function (res) {
					if(res.success==true)
                        roadDataGrid();
                    layer.close(index);
                    layer.msg(res.msg, {shift: 6});
                },"json")
			}
        }
    });
}
</script>
</html>



