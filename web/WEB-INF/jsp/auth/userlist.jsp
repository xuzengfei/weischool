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
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户帐号 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<t:datagrid name="account" actionUrl="${ pageContext.request.contextPath }/auth/user/datagrid" queryMode="group">
	<t:dgCol title="姓名" icon="maincolor" funname="show({sex})" field="name"  query="true"></t:dgCol>
	<t:dgCol title="性别" field="sex" replace="1_男,2_女" query="true"></t:dgCol>
	<t:dgCol title="长号" field="phone1" query="true" ></t:dgCol>
	<t:dgCol title="短号" field="phone2"></t:dgCol>
	<t:dgCol title="邮箱" field="email"></t:dgCol>
	<t:dgCol title="地址" field="address1"></t:dgCol>
	<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd" query="true"></t:dgCol>
	<t:dgCol title="状态"   field="isenable" replace="1_启用中,0_禁用中"></t:dgCol>
	 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	 <t:dgFunOpt funname="edit('用户信息更新','${ pageContext.request.contextPath }/auth/user/editpage','{sex}')" title="更新" icon="edit"></t:dgFunOpt>
	 <t:dgConfirmOpt url="${ pageContext.request.contextPath }/auth/user/editpage" message="是否删除" title="删除" post="add" icon="del" ></t:dgConfirmOpt>
	 <t:dgToolBar icon="add" title="添加帐号" btClass="btn-primary" funname="openWin('${ pageContext.request.contextPath }/auth',title,width,height)"></t:dgToolBar>
	 <t:dgToolBar icon="del" title="删除帐号" btClass="btn-danger" funname="add(id)"></t:dgToolBar>
</t:datagrid>
 
<script type="text/javascript">
var basePath ="${ pageContext.request.contextPath }/";
 
 
 
 
 
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}

</script>
</body>
</html>