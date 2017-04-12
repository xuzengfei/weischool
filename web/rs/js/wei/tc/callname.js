$(function () {
    loadStudent();
})

function loadStudent(){
    $.getJSON(basePath+"wei/tc/grade/"+gradeId,function (rs) {
            if(rs.success){
                var data = rs.obj;
                var html ='<div class="list-group-item">';
                if(data.pic!=""){
                    html+='<img src="'+basePath+'fileupload/thumb/'+data.pic+'" class="img-circle callNameImg">';
                }else{
                    html+='<img src="${pageContext.request.contextPath}/rs/css/wei/images/index_userImg.png" class="img-circle callNameImg">';
                }
                html+='    <div class="callNameBox">';
                html+='    <p class="stuName">'+data.stName+'</p>';
                html+='    <button class="onTimeBtn">准</button>';
                html+='    <p class="statueCall">准时</p>';
                html+='   <button class="askForLeave">请</button>';
                html+='   <p class="statueCall">请假</p>';
                html+='    <button class="skipClass">旷</button>';
                html+='    <p class="statueCall" style="margin-right: 0;">旷课</p>';
                html+='    </div>';
                html+='    </div>';
                $(".callNameList").append(html);
            }
    });
}