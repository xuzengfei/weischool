$(function(){
    $.ajaxSetup({
        async: false,
        error: function(jqXHR, textStatus, errorThrown){
            switch (jqXHR.status){
                case(500):
                    layer.msg('服务器系统内部错误', {shift: 6});
                    break;
                case(401):
                    layer.msg('登录过期', {shift: 6});
                    break;
                case(403):
                    layer.msg('没有权限', {shift: 6});
                    break;
                case(408):
                    layer.msg('请求超时', {shift: 6});
                    break;
                default:
                    layer.msg('未知错误', {shift: 6});
            }
        }
    });
})
/**
 * 打开窗口最大化
 * @param url：访问地址
 * @param title：标题名称
 */
function openWinMax(url,title){
	 var index = layer.open({
		    type: 2,
		    scrollbar: false,
		    title:title,
		    content: url,
		    area: ['500px', '500px'],
		    maxmin: true
		});
		layer.full(index);
 }

function openWin(url,title,width,height){
	 
	 var index =layer.open({
		    type: 2,
		    scrollbar: false,
		    title:title,
		    content: url,
		    area: [width+'px', height+'px'],
		    maxmin: false,
		});
}
function _href(url){
	window.location.href=url;
}
 /**
  * 预览图片
  * @param id 传入图片的ID
  * @param width
  * @param height
  */
 function viewById(id,width,height){
	 layer.open({
		    type: 1,
		    title: false,
		    closeBtn: false,
		    area: [width+'px', height+'px'],
		    skin: 'layui-layer-nobg', //没有背景色
		    shadeClose: true,
		    content: $("#"+id)
		});
 }
 /**
  * 预览图片或者视频
  * @param url：传入图片或者视频的地址
  * @param width
  * @param height
  */
 function viewByUrl(url,width,height){

	 layer.open({
		    type: 2,
		    title: false,
		    area: [width+'px', height+'px'],
		    shade: 0.8,
		    closeBtn: false,
		    shadeClose: true,
		    content: url
		});
 }
 /**
  * ajaxform提交后返回值，刷新夫窗口，并且关闭当前窗口
  * @param data
  */
 function callback(data){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.msg(data.msg);
		parent.roadDataGrid();//刷新父页面的数据列表
		parent.layer.close(index);
	}
	  
function replaceField(value, rule) {
	var strArray = rule.split(",");
	for ( var i = 0; i < strArray.length; i++) {
		var strArray1 = strArray[i].split("_");
		if (strArray1[0] == value) {
			return strArray1[1];
		}
	}
	return "";
}
/**
 * 获得datagrid搜索框里面的搜索元素名称和值,放入对象数组中
 * @param domClass class名称
 * @returns {String}
 */
function doSearch(){
	if(queryMode=="group"){
		$("."+queryMode+"Class").each(function(){
			var isDate =$(this).hasClass("Wdate");
			var name = $(this).attr("name");
			var value=$(this).val();
			if(isDate){
				if(!(name in searchParams)){
					searchParams[name] =null;
				}
			}
			 if(value!=""){
				 if(isDate)
					 searchParams[name]=strTotimestamp(value);
				 else
				 searchParams[name]=value;
			 }else{
				 delete searchParams[name];
			 }
		 });
	}else{
		var keywordfield =$("."+queryMode+"Class").attr("name");
			searchParams[keywordfield]=$("."+queryMode+"Class").val();
	}
	searchParams["pageNo"]=1;
	roadDataGrid();
}

function reloadDatagrid(response){

	parent.layer.msg(response.msg);
	if(response.success){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.roadDataGrid();//刷新父页面的数据列表
		parent.layer.close(index);
	}
	$("#Validform_msg").css("display","none");
}
/**
 * 
 * @param param 对象值，包含名称和值
 * @returns {String}
 */
function getSearchParams(param){
	var obj={};
	 for(var j in param){    
		 if(param[j]!=null){
			 obj[j] =param[j];
		 }
	 }
	 return obj;
}

/*function getSearchParams(param){
	var obj="";
	 for(var j in param){    
		 if(param[j]!=null)
		 obj +="&"+j+"="+param[j];
	 }
	 return obj;
}*/

 

 

 
/**
 * 第一页
 * 判断当前页码不能为第一页
 * @param pageNo 当前页码
 */
function firstPage(pageNo){
	if(pageNo>1){
		searchParams["pageNo"]=1;
		roadDataGrid();
	}
}
/**
 * 上一页
 * 判断当前页码不能为第一页，然后减去1
 * @param pageNo 当前页码
 */
function prevPage(pageNo){
	if(pageNo>1){
		searchParams["pageNo"]=pageNo-1;
		roadDataGrid();
	}
}
/**
 * 下一页
 * 判断当前页码不能为最后一页，然后加上1
 * @param pageNo 当前页码
 * @param pageCount 最后一页
 */
function nextPage(pageNo,pageCount){
	if(pageNo<pageCount){
		searchParams["pageNo"]=pageNo+1;
		roadDataGrid();
	}
}
/**
 * 最后一页
 * @param pageNo
 * @param pageCount
 */
function lastPage(pageNo,pageCount){
	if(pageNo<pageCount){
		searchParams["pageNo"]=pageCount;
		roadDataGrid();
	}
}
function currentPage(pageNo,n){
	if(pageNo!=n){
		searchParams["pageNo"]=n;
		roadDataGrid();
	}
}

//时间格式化
Date.prototype.format = function (format,value) {
	var curTime=null;
	try {
		  if (!format) {
					format = "yyyy-MM-dd hh:mm:ss";
		  }
		  var datetime = new Date();
		   datetime.setTime(value);
		    var year = datetime.getFullYear();
		    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		    if(format.indexOf("yyyy")>-1){
		    	format =format.replace("yyyy", year);
		    }
		    if(format.indexOf("MM")>-1){
		    	format =format.replace("MM", month);
		    }
		    if(format.indexOf("dd")>-1){
		    	format =format.replace("dd", date);
		    }
		    if(format.indexOf("HH")>-1){
		    	format =format.replace("HH", hour);
		    }
		    if(format.indexOf("mm")>-1){
		    	format =format.replace("mm", minute);
		    }
		    if(format.indexOf("ss")>-1){
		    	format =format.replace("ss", second);
		    }
		    curTime =format;
	} catch (e) {
		curTime ="日期格式不正确!";
	}
	
	    
	    return curTime;
};
$(function(){
	//焦点定位于第一个可输入的文本框
	if(!$("input:visible:first").hasClass("Wdate")) $("input:visible:first").focus();
	//validform自定义datatype,dot(正数1-4位,小数0-2位,0,0.0,0.00不通过)
	$.extend($.Datatype,{
		"dot" : /^(([1-9][\d]{0,5}\.[\d]{1,4})|(0\.[\d]{0,4})|([1-9][\d]{0,4}))$/
	})
	$.extend($.Tipmsg,{
		w:{"dot" : "整数1-4位,小数0-4位,格式:1000.00,1000.0,0.01"}
	})
});
function datagridConfirm(url,message,post){
	if(url=="null"||url==""){
		layer.msg('地址不能为空', {shift: 6});
		return;
	}
	if(post=="null"||url==""){
		layer.msg('请填写提交类型GET/POST/DELETE/PUT', {shift: 6});
		return;
	}	
	layer.confirm(message, {
	    btn: ['是','否'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		$.ajax({
            type:post,
            url:url,
            datatype: "json", 
            async:false,
            success:function(data){
            	layer.msg(data.msg, {shift: 6});
            	roadDataGrid();//刷新列表
            },
            //调用出错执行的函数
            error: function(){
            	layer.msg('网络异常', {shift: 6});
            }         
         });
	}, function(){
	    layer.msg('决定好再操作', {shift: 6});
	});
}
/**
 * 批量删除
 * @param url
 * @param dagagridName
 * @param message
 */
function bathDel(url,dagagridName,message){//批量删除
	var ids = selectIds(dagagridName);
	url+="&id="+ids;
	if(ids==""){
		layer.msg('请至少选择一项', {shift: 6});
		return;
	}
	datagridConfirm(url,message,'DELETE');
}
 /**
  * 获得datagrid的选中ID
  * @param datagridName
  * @returns {String}
  */
function selectIds(datagridName){
	var ids ='';
	$('input:checkbox[name='+datagridName+'_checkbox]:checked').each(function(i){
			if(0==i){
	        ids = $(this).val();
	       }else{
	        ids += (','+$(this).val());
	       }
	})
	return ids;
} 
/**
 * 去除字符串(以逗号分隔)
 * @param delstr 去除的字符串
 * @param tgtstr 目标字符串
 * @returns
 */
function delStr(delstr,tgtstr){
	if (delstr != "")
	{
		var regx = new RegExp("("+delstr+",)|(,"+delstr+")","g");
		tgtstr = tgtstr.replace(regx,"");
	}
	if (delstr == tgtstr)
	{
		tgtstr="";
	}
	return tgtstr;
}
/** 过滤非数字字符 */
function fmD(obj) {
	obj.value = obj.value.replace(/[^\d]/g, '');
	return obj.value;
}

/** 过滤非数字、小数点字符 */
function fmDD(obj) {
	obj.value = obj.value.replace(/[^\d.]/g, '');
	return obj.value;
}
function strTotimestamp(date){
	if(date==null||date=="")
		return;
    return  Date.parse(new Date(date));
}
(function ($) {
    $.extend({
        myTime: {
            /**
             * 当前时间戳
             * @return <int>        unix时间戳(秒)
             */
            CurTime: function () {
                return Date.parse(new Date()) / 1000;
            },
            /**
             * 日期 转换为 Unix时间戳
             * @param <string> 2014/01/01 20:20:20  日期格式
             * @return <int>        unix时间戳(秒)
             */
            DateToUnix: function (string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('/', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                    parseInt(d[0], 10) || null,
                    (parseInt(d[1], 10) || 1) - 1,
                    parseInt(d[2], 10) || null,
                    parseInt(t[0], 10) || null,
                    parseInt(t[1], 10) || null,
                    parseInt(t[2], 10) || null
                )).getTime();
            },
            /**
             * 时间戳转换日期
             * @param <int> unixTime    待时间戳(毫秒)
             * @param format 转出类型：yyyy-MM-dd h:m:s
             */
            UnixToDate: function (unixTime, format) {
                if (unixTime == null || unixTime == "null" || unixTime == "" || typeof (unixTime) == "undefined") {
                    return "";
                }
                var d = new Date();
                d.setTime(unixTime);
                return d.format1(format);
            }
        }
    });
})(jQuery);

Date.prototype.format1 = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}