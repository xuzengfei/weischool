var week =0;
$(function () {
    list();
})

/**
 * 列出用户
 */
function list() {
    $.getJSON(basePath + "wei/st/timetable/"+week, function (res) {
        if (res.success) {
            $(".dayclass").children().remove();
            var result = res.attributes;
            console.log(result.month);
            $("#monthNum").html(cnMonth(parseInt(result.month)));
             $.each(result.date,function (i,item) {
                 $(".day"+i).html(item.split("-")[2]);
                 $(".day"+i+"_").attr("name","day_"+item.split("-")[2]);
             })
            $.each(result.data,function (i,item) {
                var start = $.myTime.UnixToDate(item.start, "hh:mm");
                var end = $.myTime.UnixToDate(item.end, "hh:mm");
                $("div[name=day_"+item.date+"]").append('<div class="classDetail"> <p>'+item.gradName+'</p> <p class="start">'+start+'</p>-- <p class="over">'+end+'</p> </div>');
            })
            classTableColor();
        }
    });
}
function cnMonth(month) {
    switch (month){
        case 1:return "一";
        case 2:return "二";
        case 3:return "三";
        case 4:return "四";
        case 5:return "五";
        case 6:return "六";
        case 7:return "七";
        case 8:return "八";
        case 9:return "九";
        case 10:return "十";
        case 11:return "十一";
        default:return "十二";
    }
}

//控制课程表随机颜色块
function classTableColor() {
    var color=new Array("#1bb9e0","#ff7e50","#50ffb7","#ff5050");
    $(".classDetail").each(function(){
        var c=Math.floor(Math.random()*3);
        $(this).css("background-color",color[c]);
    });
}


$(function(){
    $(".classDetail").each(function(){
        var s=$(this).find(".start").text();
        var o=$(this).find(".end").text();
        var ss=parseInt(s.split(":"));
        var oo=parseInt(o.split(":"));
        var h=$(this).height();
        if(oo-ss>=3){
            $(this).css("height",3/2*h+"px");
        }
        //if(ss>12){
        //    $(this).css("top","60%");
        //}
    });
});
$(function () {
    $("#before").click(function () {
        $(this).fadeOut(200);
        week--;
        list();
        $(this).fadeIn(200);
    });
    $("#after").click(function () {
        $(this).fadeOut(200);
        week++;
        list();
        $(this).fadeIn(200);
    });
});
