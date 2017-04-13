$(function () {
    loadStudent();
})

function loadStudent(){
    $.getJSON(basePath+"wei/tc/grade/"+gradeId,function (rs) {
            if(rs.success){
                var data = rs.obj;
                $.each(data,function (i,item) {
                    var html ='<div class="list-group-item" id="'+item.stId+'" sgId="'+item.id+'" grId=""  >';
                    if(item.pic!=""){
                        html+='<img src="'+basePath+'fileupload/thumb/'+item.pic+'" class="img-circle callNameImg">';
                    }else{
                        html+='<img src="${pageContext.request.contextPath}/rs/css/wei/images/index_userImg.png" class="img-circle callNameImg">';
                    }
                    html+='    <div class="callNameBox">';
                    html+='    <p class="stuName">'+item.stName+'</p>';
                    html+='    <button class="onTimeBtn">准</button>';
                    html+='    <p class="statueCall">准时</p>';
                    html+='   <button class="askForLeave">请</button>';
                    html+='   <p class="statueCall">请假</p>';
                    html+='    <button class="skipClass">旷</button>';
                 /*   html+='    <p class="statueCall" style="margin-right: 0;">旷课</p>';*/
                    html+='    </div>';
                    html+='    </div>';
                   $(".callNameList").append(html);
                })
                if(data.length>0){
                    //TODO 测试
                    $.getJSON(basePath+"wei/tc/grade/reg/"+gtId,function (res) {
                            if(res.success){
                                   var dataR =  res.obj;
                                   $.each(dataR,function (n,it) {
                                         $("#"+it.stId ).attr("grId",it.id)
                                       if(it.status==1){
                                           $("#"+it.stId ).children(".onTimeBtn").trigger("click");
                                       }else if(it.status==2){
                                           $("#"+it.stId ).children(".askForLeave").trigger("click");
                                       }else{
                                           $("#"+it.stId ).children(".skipClass").trigger("click");
                                       }
                                   })
                            }
                    });
                }
            }
       /* callNameBox();*/
    });
}

//教师点名页面考勤按钮点击颜色控制
$(function () {
    $(".callNameList").on("click1",".onTimeBtn",function () {
        $(this).parent().find("button").css("background-color", "#f2f2f2");
        $(this).parent().find("button").css("color", "#000000");
        $(this).parent().find(".onTimeBtn").css("background-color", "#1bb9e0");
        $(this).parent().find(".onTimeBtn").css("color", "#ffffff");
        return false;
    }).on("click1",".askForLeave",function () {
        $(this).parent().find("button").css("background-color", "#f2f2f2");
        $(this).parent().find("button").css("color", "#000000");
        $(this).parent().find(".askForLeave").css("background-color", "#64d8ae");
        $(this).parent().find(".askForLeave").css("color", "#ffffff");
        return false;
    }).on("click1",".skipClass",function () {
        $(this).parent().find("button").css("background-color", "#f2f2f2");
        $(this).parent().find("button").css("color", "#000000");
        $(this).parent().find(".skipClass").css("background-color", "#f26d3e");
        $(this).parent().find(".skipClass").css("color", "#ffffff");
        return false;
    }).on("click",".onTimeBtn",function () {
        sign($(this),1);
    }).on("click",".askForLeave",function () {
        sign($(this),2);
    }).on("click",".skipClass",function () {
        sign($(this),3);
    })

})
//TODO ajax请求数据库.测试
function sign(obj,status) {
    var item =$(obj).parent().parent(".list-group-item");
    var grId =$(item).attr("grId");
    if(grId==""||typeof (grId)=="undefined"){
        alert($(item).attr("id"));
        $.post(basePath+"wei/tc/grade/reg/studentgrade/"+$(item).attr("id")+"/status/"+status ,function(res){
            if(res.success){
                $(obj).trigger("click1");
            }else{
                layer.msg('点名失败！', {shift: 6});
            }
        }, "json");
    }else{
        $.post(basePath+"grade/reg/"+grId+"/status/"+status ,function(res){
            if(res.success){
                $(obj).trigger("click1");
            }else{
                layer.msg('点名失败！', {shift: 6});
            }
        }, "json");
    }


}
