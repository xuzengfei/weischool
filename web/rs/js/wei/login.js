/***
 * 登录的时候需要渲染的方法
 */
$(function () {
    listCampus();
})
/**
 * 登录执行方法
 */
function login(){
    $.post(basePath+"wei/user/login", $("#loginform").serialize(),
        function(rs){
            layer.open({
                content: rs.msg
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
            if(rs.success){
                window.location.href=basePath+"wei/to/index";
            }
        }, "json");
}
/**
 * 列出校区，显示到下拉框中
 */
function  listCampus() {
    $.getJSON(basePath+"wei/campus/list", function(res){
         if(res.success){
             $.each(res.obj,function(i,it){
                 $("#cpId").append("<option value=\""+it.id+"\">"+it.name+"</option>")
             })
         }
    });
}
