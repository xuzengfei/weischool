var pageNo = 1;
var recordCount = 0;//总记录数
$(function () {
    listInform(); //列出公告
})

/**
 * 列出用户
 */
function listInform() {
    $.getJSON(basePath + "wei/st/notice/datagrid?pageNo=" + pageNo, function (res) {
        if (res.success) {
            var data = res.obj.datas;//列表
            recordCount = res.obj.recordCount;
            $.each(data, function (i, item) {
                if (pageNo == 1 && i == 0) {
                    $(".headTitle").parent().attr("href",basePath + "wei/st/notice/to/msg/view/"+item.id);
                    $(".headTitle p").html(item.title);
                    if (item.pic == "null" || item.pic == ""||data.pic == null)
                        $(".headTitle img").attr("src", basePath + "rs/css/wei/images/inform.png");
                    else
                        $(".headTitle img").attr("src", basePath + "fileupload/thumb/" + item.pic);
                } else {
                    var newInform = "";
                    if (item.isNew == 1)
                        newInform = "   <img src=\"" + basePath + "rs/css/wei/images/new.png\" class=\"new\">";

                    $(".list-group").append("<a href=\""+basePath + "wei/st/notice/to/msg/view/"+item.id+"\">" +
                        "   <div class=\"list-group-item\">" +
                        newInform +
                        "   <p class=\"title\">" + item.title + "</p>" +
                        "<p class=\"date\">" + $.myTime.UnixToDate(item.createTime, "yyyy-MM-dd") + "</p>" +
                        "   </div>" +
                        "   </a>");
                }

            })

        }
    });
}
/**
 * 瀑布流
 * @type {boolean}
 */
var loading = false;
$(document.body).infinite().on("infinite", function () {
    $(".weui-infinite-scroll").css("display", "");
    if (loading) return;
    loading = true;
    setTimeout(function () {
        var dataLength = $(".list-group-item").length;
        if (dataLength >= recordCount - 1)//还没有达到最大的总数后，可以翻页
        {
            $(".weui-infinite-scroll").html("暂无数据");
            return;
        }
        pageNo++;
        listInform();
        $(".weui-infinite-scroll").css("display", "none");
        loading = false;
    }, 1000);
});
