$(function () {
    getInform(); //列出公告
})

/**
 * 列出用户
 */
function getInform() {
    $.getJSON(basePath + "wei/notice/get/msg/" + moduleId, function (res) {
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
                $(".certificateContent").html(data.content);

        }
    });
}
