$(function () {
    getCpLife(); //列出公告
})

/**
 * 列出用户
 */
function getCpLife() {
    $.getJSON(basePath + "wei/st/cplife/get/msg/" + moduleId, function (res) {
        if (res.success) {
            var data = res.obj;
            if (data.pic == "null" || data.pic == ""||data.pic == null)
                $(".certificateImg").css("display", "none");
            else{
                $(".certificateImg").attr("src", basePath + "fileupload/thumb/" + data.pic);
            }

                $(".certificateDate").html($.myTime.UnixToDate(data.createTime, "yyyy-MM-dd"));
                $(".certificateImg").attr("src", basePath + "fileupload/thumb/" + data.pic);
                $(".certificateTitle").html(data.title);
                $(".certificateContent").html(data.remark);

        }
    });
}
