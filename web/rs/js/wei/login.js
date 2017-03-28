/***
 * 登录的时候需要渲染的方法
 */
$(function () {
          /**
     * 列出校区，显示到下拉框中
     */
    $(".dropdown").click(function () {
        $.getJSON(basePath+"wei/st/campus/list", function(res){
            if(res.success){
                $.each(res.obj,function(i,it){
                    // $("#cpId").append("<option value=\""+it.id+"\">"+it.name+"</option>")
                    $(".dropdown-menu").append("<li onclick=\"chooseCp('"+it.id+"')\"'><a  tabindex=\"-1\" data-toggle=\"tab\" >"+it.name+"</a></li>");
                })
            }
            $(".dropdown-menu").children("li").each(function () {
                $(this).click(function () {
                    var area = $(this).children().text();
                    $("#cp").html(area + "<b class='caret'></b>");
                    $(this).remove();

                });
            });
        });
    })
})
/**
 * 登录执行方法
 */
function login(){
    $.post(basePath+"wei/st/user/login", $("#loginform").serialize(),
        function(rs){
            layer.open({
                content: rs.msg
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
            if(rs.success){
                window.location.href=basePath+"wei/st/to/index";
            }
        }, "json");
}

function chooseCp(id) {
    $("#cpId").val(id);
}