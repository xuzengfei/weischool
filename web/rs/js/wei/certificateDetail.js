$(function () {
    getInform(); //列出公告
})

/**
 * 列出用户
 */
function getInform() {
    $.getJSON(basePath + "wei/st/cert/get/msg/" + moduleId, function (res) {
        if (res.success) {
            var data = res.obj;
            if (data.pics == "null" || data.pics == ""||data.pics == null)
                $(".certificateImg").css("display", "none");
            else{

                $.each(data.pics,function (i,val) {
                    $(".certificateContent").after('<img class="certificateImg" src="'+basePath + 'fileupload/thumb/' + val+'">');
                })

            }

            $(".certificateDate").html($.myTime.UnixToDate(data.createTime, "yyyy-MM-dd"));
            $(".certificateTitle").html(data.remark);
            $(".certificateContent").html("编号："+data.no);


        }
    });
}
