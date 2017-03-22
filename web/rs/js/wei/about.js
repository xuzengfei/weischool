$(function () {
    list();
})

/**
 * 列出用户
 */
function list() {
    $.getJSON(basePath + "wei/st/about/sc/get", function (res) {
        if (res.success) {
            var data = res.obj;
                $(".certificateDate").html($.myTime.UnixToDate(data.createTime, "yyyy-MM-dd"));
                $(".certificateTitle").html(data.title);
                $(".certificateContent").html(data.remark);
        }
    });
}
