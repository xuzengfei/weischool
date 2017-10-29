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
	<t:base type="H-ui.css,icon.css,jquery,layer,laypage,tools"></t:base>
	<title>教师选择框</title>
	<script type="text/javascript">
		var gtId ="${gtId}";
		var gradeId="${gradeId}";
		var gradeName="${gradeName}";
		var signTime ="${signTime}";

        function filterData(){
            var ids = parent.getIds();
            $(".table").find(".text-c").each(function(i){
                if(i>0){
                    var data=$(this).children().eq(1).html();
                    for(var n=0;n<ids.length;n++){
                        if(ids[n]==data){
                            $(this).remove();
                            delete ids[n];
                            break;
                        }
                    }
                }
            })
        }
        function sign() {
            var stId ="";
            var stName="";
            $(".table").find(".text-c").each(function(i){
                if(i>0){
                     if($(this).children().eq(0).children().prop("checked")==true){
                         stId+=","+$(this).children().eq(1).html();
                         stName+=","+$(this).children().eq(3).html();
					 }
                }
            })
			if(stId==""){
                layer.msg("请选择一个用户！");
                return;
			}

            layer.confirm("确认执行此操作？", {
                btn: ['是','否'], //按钮
                shade: false //不显示遮罩
            }, function(){
                stId=stId.substring(1,stId.length);
                stName=stName.substring(1,stName.length);
                var status = $("#status").val();
                $.post("${ pageContext.request.contextPath }/web/manager/gradereg/sign",{gradeId:gradeId,gradeName:gradeName,stId:stId,stName:stName,gtId:gtId,signTime:signTime,status:status},
                    function (res) {
                        if(res.success)
                            parent.roadDataGrid();
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    })
            }, function(){
                layer.msg('决定好再操作', {shift: 6});
            });

        }
	</script>
</head>
<body>
<div class="pd-20">
	<div class="row cl">
		<label class="form-label col-3">上课状态：</label>
		<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" id="status" name="status" size="1" >
					 <option  value="1">准时</option>
					 <option   value="2">请假</option>
					 <option   value="3">旷课</option>
					 <option   value="4">迟到</option>
				</select>
				</span>
		</div>
	</div>
</div>
<t:datagrid name="student" actionUrl="${ pageContext.request.contextPath }/web/manager/student/grade/datagrid/grade/${gradeId}"  pagination="false" checkbox="true" queryMode="group" onLoadSuccess="filterData()">
	<t:dgCol title="ID"   field="student.id"  hidden="true"></t:dgCol>
	<t:dgCol title="学号"   field="student.no"   ></t:dgCol>
	<t:dgCol title="姓名"  field="student.name"  ></t:dgCol>
</t:datagrid>
<div class="pd-20">

	<div class="row cl">
		<div class="col-9 col-offset-3">
			<input class="btn btn-primary radius" type="button" onclick="sign()" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
</div>

</body>
</html>