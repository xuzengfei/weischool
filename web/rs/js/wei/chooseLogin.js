var id ="";
var cpId="";
var stId="";
var openId="";
/**
 * 登录执行方法
 */
function login(){
    if(id==""||cpId==""||stId==""||openId==""){
        layer.msg('请选择学生', {shift: 6});
        return;
    }

    $.post(basePath+"wei/st/user/to_user", {id:id,cpId:cpId,stId:stId,openId:openId},
        function(rs){
            if(rs.success){
                window.location.href=basePath+"wei/st/to/index";
            }else{
                layer.msg('进入失败', {shift: 6});
            }
        }, "json");
}

function chooseUser(id,openId,stId,cpId) {
    this.id=id;
    this.openId=openId;
    this.stId=stId;
    this.openId=openId;
}