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
    <t:base type="H-ui.css,icon.css,tree.css,jquery,layer,laypage,wdate,tools,tree,treesuper"></t:base>
    <title>管理员列表</title>
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<body>

<table class="table">
     <tr  >
         <td class="va-t" colspan="2" >
             <div class="text-c">
                 <input type="text" name="" id="keyword" placeholder="输入名称" style="width:100px;height: 25px;font-size: 11px" class="input-text">
                 <button name="" id="" onclick="search()" class="btn btn-success" type="submit" style="height: 25px;font-size: 11px"><i class="Hui-iconfont"></i>查询</button>
             </div>
         </td>

     </tr>
    <tr>
        <td width="200" class="va-t">
            <ul id="tree" class="ztree"></ul>
        </td>
        <td class="va-t">
                <div>
                        <dl class="">
                            <dd>
                                <dl class="cl permission-list2">
                                    		  暂无数据!
                                </dl>
                            </dd>
                        </dl>
                </div>
        </td>
    </tr>
</table>
<script type="text/javascript">
    var basePath = '${ pageContext.request.contextPath }/';


    var demoIframe;

    var setting = {
        edit: {
            enable: false
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function (treeId, treeNode) {
              /* alert(treeNode.id+"  "+treeNode.floor);*/
                $.ajax({
                    type: "GET",
                    url: basePath + 'web/manager/utools/userlist/st/'+treeNode.floor+"/"+treeNode.id,
                    datatype: "json",
                    async: false,
                    success: function (rs) {
							setStData(rs);	
                    }
                });
                return true;
            }
        }
    };

    $(document).ready(function () {
        loadTree();

    });

    function loadTree() {
        $.ajax({
            type: "GET",
            url: basePath + 'web/manager/utools/tree',
            datatype: "json",
            async: false,
            success: function (data) {
                var t = $("#tree");
                t = $.fn.zTree.init(t, setting, data);
            }
        });
    }
$(function(){
	 $(".permission-list2").on("click","input", function() {
         parent.reAllStObj();
         parent.setStObj($(this).attr("id"),$(this).attr("title"));
	 })
})

function search(){
	var keyword =$("#keyword").val();
	if(keyword=="")
		return;
	 $.ajax({
         type: "GET",
         url: basePath + 'web/manager/utools/userlist/st/0/-1?keyword='+keyword,
         datatype: "json",
         async: false,
         success: function (rs) {
        	 setStData(rs);
         }
     });
}
function setStData(rs){
	if(rs.success){
		var stObj = parent.returnStObj();
		var data = rs.obj;
		var str="";
		$.each(data,function(i,val){
			if(i%3==0){
				str+="<dd>"
			}
		 
			if(stObj[val.id]==val.name){
				str+=" <label class=\"\"><input type=\"radio\" checked=\"checked\" value=\"\" name=\"st\" id=\""+val.id+"\" title=\""+val.name+"\" >"+val.name+"</label>";
			}else{
				str+=" <label class=\"\"><input type=\"radio\" value=\"\" name=\"st\" id=\""+val.id+"\" title=\""+val.name+"\"  >"+val.name+"</label>";
			}
			if((i+1)%3==0){
				str+="</dd>";
			}
		})
		if(str=="")
			$(".permission-list2").html("暂无数据！");
		else
			$(".permission-list2").html(str);
	}
}
</script>
</body>
</html>