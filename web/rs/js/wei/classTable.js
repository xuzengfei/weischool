$(function () {
    list(0);
})

/**
 * 列出用户
 */
function list(week) {
    $.getJSON(basePath + "wei/st/timetable/"+week, function (res) {
        if (res.success) {
            var result = res.attributes;
            $(".mouth span").html(cnMonth(parseInt(result.month)));
             $.each(result.date,function (i,item) {
                 $(".day"+i).html(item.split("-")[2]);
                 $(".day"+i+"_").attr("name","day_"+item.split("-")[2]);
             })
            $.each(result.data,function (i,item) {
                var start = $.myTime.UnixToDate(item.start, "hh:mm");
                var end = $.myTime.UnixToDate(item.end, "hh:mm");
                $("div[name=day_"+item.date+"]").append('<div class="classTime"> <p>'+item.gradName+'</p> <p class="start">'+start+'</p>-- <p class="over">'+end+'</p> </div>');
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