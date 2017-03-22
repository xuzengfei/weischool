var pageNo = 1;
var recordCount = 0;//总记录数
$(function () {
    listData(); //列出
})

/**
 * 列出用户
 */
function listData() {
    $.getJSON(basePath + "wei/st/cplife/datagrid?pageSize=2&pageNo=" + pageNo, function (res) {
        if (res.success) {
            var data = res.obj.datas;//列表
            recordCount = res.obj.recordCount;
            $.each(data, function (i, item) {
                var date =$.myTime.UnixToDate(item.st, "yyyy年MM月dd HH时mm分");
                  $(".weui-infinite-scroll").before(
                                    "<a href='"+basePath+"wei/st/cplife/to/view/"+item.id+"'>" +
                                    "<div class='lifeBox'>" +
                                    "<p class='date'>"+date+"</p>" +
                                    "<div class='photo'>" +
                                    "<img src='"+basePath + "fileupload/thumb/" + item.pic+"'>" +
                                    "<p>"+item.title+"</p>" +
                                    "</div>" +
                                    "</div>" +
                                    "</a>");
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
        var dataLength = $(".lifeBox").length;
        if (dataLength >= recordCount)//还没有达到最大的总数后，可以翻页
        {
            $(".weui-infinite-scroll").html("暂无数据");
            return;
        }
        pageNo++;
          listData()
        $(".weui-infinite-scroll").css("display", "none");
        loading = false;
    }, 1000);
});

