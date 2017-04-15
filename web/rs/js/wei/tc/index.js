$(function () {
    list();
})
/**
 * 列出用户
 */
function list() {
    $.getJSON(basePath + "wei/tc/data", function (res) {
        if (res.success) {
            var data = res.obj;
            var week = data.week;
            var date = data.date;
            var tcName =data.tcName;
            var emNo = data.emNo;
            var gradeDatas =data.gradeDatas;
            var weekday;
            switch (week){
                case 1: weekday="星期一";break;
                case 2: weekday="星期二";break;
                case 3: weekday="星期三";break;
                case 4: weekday="星期四";break;
                case 5: weekday="星期五";break;
                case 6: weekday="星期六";break;
                default: weekday="星期日";break;
            }

            $(".classTop h3").html(tcName);
            $(".classTop h5").html("任教工号："+emNo);
            $(".classTop h4").html(date);
            $.each(gradeDatas,function(key,value){
                var html="<div class=\"list-group\">";
                html+="<div class=\"addressChoose\">";
                html+="     <img src=\""+basePath+"/rs/css/wei/images/teach_point.png\" class=\"point\">";
                html+="   <p>"+key+"</p>";
                html+="  </div>";
                $.each(value,function (i,item) {
                    var status ;
                    if(item.status==0)
                        status="还未开始";
                    else if(item.status==1)
                        status="正在上课";
                    else
                        status="课程结束";

                    html+="     <div class=\"list-group-item\">";
                    html+="     <button class=\"statue\">"+status+"</button>";
                    html+="     <p class=\"className\">"+item.name+"</p>";
                    html+="      <p class=\"time\">"+weekday+" "+$.myTime.UnixToDate(item.st, "hh:mm")+"-"+$.myTime.UnixToDate(item.et, "hh:mm")+"</p>";
                    html+="  <a href=\"";
                    if(item.status>0){
                        html+=basePath+"wei/tc/callname/grade/"+item.id+"/gradeTime/"+item.gtId;
                    }else{
                        html+="teach_callName.html";
                    }

                    html+="\"><button class=\"callNameBtn\">点名</button></a>";
                    html+="      </div>";
                })
                html+="     </div>";
                $(".container").append(html);
                $("#nulldata").remove();
            })
            teacherIndex();
        }
    });
}

