/***
 * 登录的时候需要渲染的方法
 */
$(function () {

})
/**
 * 登录执行方法
 */
function login(){
    $.post(basePath+"wei/tc/login", $("#loginform").serialize(),
        function(rs){
            layer.open({
                content: rs.msg
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
            if(rs.success){
                window.location.href=basePath+"wei/tc/to/index";
            }
        }, "json");
}
