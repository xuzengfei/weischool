 
var encryptCacheData;
//回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		Login();
	}
});

function Login(){
	var acc =$("#account").val();
	var pasw=$("#password").val();
	var code = $("#validateCode").val();
	if(acc==""){
		layer.msg('帐号不能为空！', {shift: 6});
		$("#account").focus();
		return;
	}
	if(pasw==""){
		layer.msg('密码不能为空！', {shift: 6});
		$("#password").focus();	
		return;
	}
	if(code==""){
		layer.msg('验证码不能为空！', {shift: 6});
		$("#validateCode").focus();
		return;
	}
	getKey();
	$.ajax({
		url:basePath+'auth/login/checkuser',
		dataType:'json',
		data:{longkey:encryptCacheData},
		type:'post',
		cache : false,
		async:false,
		success:function(data){
			layer.msg(data.msg, {shift: 6});
			if(data.success==true){
				checkBox();
				window.location.href=basePath+"auth/login";
			}else{
				 if(data.obj==0){
					 changeCode();
					 $("#account").select();
				 }
				 if(data.obj==-1){
					 changeCode();
					 window.location.reload();
				 }
				 if(data.obj==-2){
					 changeCode();			
					 $("#validateCode").select();
				 }
			}	
		},
		error:function(){
			layer.msg('网络异常！', {shift: 6});
		}
		
	});	
}

function getKey(){
	$.ajax({
		url:basePath+'auth/login/checkKey?time='+ Math.floor(Math.random()*100),
		datatype:'json',
		type:"get",
		async:false,
		success:function(data){
			if(data.success==true){
				// 页面里，Javascript对明文进行加密：
				var key = RSAUtils.getKeyPair(data.attributes.exponent, '', data.attributes.modulus);
				var acc =$("#account").val();
				var pasw=$("#password").val();
				var code = $("#validateCode").val();
				var time =  data.attributes.time;
				if(acc.length>0&&pasw.length>0){
				  encryptCacheData = RSAUtils.encryptedString(key,code+","+time+","+encodeURIComponent(acc)+","+encodeURIComponent(pasw));	
			     } 
			}else{
				layer.msg(data.msg, {shift: 6});
		 
			}
    	}
	});
}

 
 function checkBox()	{
	 var box = document.getElementById("rmbPwd");
		if (box.checked) {
			setCookie("account",$("#account").val());
			setCookie("password",$("#password").val());
			//监听复选框
		} else {
			setCookie("account","");
			setCookie("password","");
		}
 }
 
 function setCookie (name, value) {
	 var Days = 14; 
	 var exp = new Date(); 
	 exp.setTime(exp.getTime() + Days*24*60*60*1000); 
	 document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
 }
 
 function getCookie (name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	    return unescape(arr[2]); 
	else 
	    return null;
 }
 
 function setActPsw (){
	 var account = getCookie("account");
	 var password = getCookie("password");
	 
	 $("#account").val(account);
	 $("#password").val(password);
	 if(password!=null){
	 	$("#rmbPwd").prop("checked", true);
	 }
	 
 }
 
 $(function(){        
	    $('#kaptchaImage').click(function () {//生成验证码  
	     $(this).hide().attr('src', basePath+'auth/captcha?time' + Math.floor(Math.random()*100) ).fadeIn();  
	     event.cancelBubble=true;  
	    }).css("height","40px");  
	     setActPsw();
});   
 function changeCode() {  
	    $('#kaptchaImage').hide().attr('src', basePath+'auth/captcha?time' + Math.floor(Math.random()*100) ).fadeIn();  
	    $("#validateCode").val("");
	    event.cancelBubble=true;  
	}  
 