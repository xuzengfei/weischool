/***
 * 登录的时候需要渲染的方法
 */
$(function () {
    listUser();
    loadPic();
    //进入课堂选择界面
    $("#classQuery").parent().click(function(){
        window.location.href=basePath+"wei/st/course/to/choose";
    })
    $("#cordQuery").parent().click(function(){
        window.location.href=basePath+"wei/st/to/cordQuery";
    })
    $("#inform").parent().click(function(){
        window.location.href=basePath+"wei/st/to/inform";
    })
    $("#album").parent().click(function(){
        window.location.href=basePath+"wei/st/to/book";
    })
    $("#certificate").parent().click(function(){
        window.location.href=basePath+"wei/st/to/certificate";
    })
    $("#schoolLife").parent().click(function(){
        window.location.href=basePath+"wei/st/to/schoolLife";
    })
    $("#about").parent().click(function(){
        window.location.href=basePath+"wei/st/to/about";
    })
    $("#classTable").parent().click(function(){
        window.location.href=basePath+"wei/st/to/classTable";
    })

    $(".dropdown").click(function () {
        if($(".sc_class").length==0){
            $.getJSON(basePath+"wei/st/st/get/campus", function(res){
                if(res.success){
                    $.each(res.obj,function (i,item) {
                        $(".dropdown-menu").append("<li class=\"sc_class\" onclick=\"changeCp('"+item.id+"')\"><a   tabindex=\"-1\" data-toggle=\"tab\" >"+item.name+"</a></li>");
                    })
                }
                $(".dropdown-menu").children("li").each(function () {
                    $(this).click(function () {
                        var area = $(this).children().text();
                        $(".dropdown").html(area + "<b class='caret'></b>");
                        $(this).parent().remove();
                    });
                });
            });


        }

    })
})
/**
 * 列出用户
 */
function  listUser() {
    $.getJSON(basePath+"wei/st/st/grade", function(res){
        if(res.success){
            var restClass =res.obj[0].restClass;
            $("#stName").html(res.obj[0].stName);
            $("#restClass").html(restClass);
            $("#gradeName").html(res.obj[0].gradeName);
            $("#cpName").html(res.obj[0].cpName+"<b class='caret'></b>");
            if(restClass<20){
                $("#restClass").css({"font-weight":"bold","color":"red"})
            }
        }
    });
}

function loadPic(){
    loadStPic(basePath);
}
function changeCp(id) {
    $.getJSON(basePath+"wei/st/user/change/cp/"+id, function(res){
      if(res.success){
          window.location.href=basePath+"wei/st/to/index";
        }
    });
}
