/***
 * 登录的时候需要渲染的方法
 */
$(function () {
    listUser();
    loadPic();
    //进入课堂选择界面
    $("#classQuery").parent().click(function(){
        window.location.href=basePath+"wei/course/to/choose";
    })
    $("#cordQuery").parent().click(function(){
        window.location.href=basePath+"wei/to/cordQuery";
    })
    $("#inform").parent().click(function(){
        window.location.href=basePath+"wei/to/inform";
    })
    $("#album").parent().click(function(){
        window.location.href=basePath+"wei/to/book";
    })

})
/**
 * 列出用户
 */
function  listUser() {
    $.getJSON(basePath+"wei/st/grade", function(res){
        if(res.success){
            $("#stName").html(res.obj[0].stName);
            $("#restClass").html(res.obj[0].restClass);
            $("#gradeName").html(res.obj[0].gradeName);
            $("#cpName").html(res.obj[0].cpName+"<b class='caret'></b>");
        }
    });
}

function loadPic(){
    loadStPic(basePath);
}
