var pageNo = 1;
var recordCount = 0;//总记录数
$(function () {
    listBook(); //列出公告
})

/**
 * 列出用户
 */
function listBook() {
    $.getJSON(basePath + "wei/st/expe/datagrid?pageNo=" + pageNo, function (res) {
        if (res.success) {
            var data = res.obj.datas;//列表
            recordCount = res.obj.recordCount;
            $.each(data, function (i, item) {
                var date =$.myTime.UnixToDate(item.expeTime, "MM月dd");
                var str =' <div class="list">';
                var isExist =0;
                if($(".date").length>0){
                    $(".date").each(function () {
                        if($(this).text()==date){
                            isExist=1;

                            return false;
                        }
                    })
                }
                if(isExist==0){
                    str+='<div class="dateBox"> <img src="'+basePath+'rs/css/wei/images/littlePoint.png"> <p class="date">'+date+'</p> </div>';
                }
                str+=' <div class="listContent"> <p>'+item.remark+'</p>';

                $.each(item.picPaths,function (i,v) {
                    str+='<img src="'+basePath+'fileupload/thumb/' + v+'" >';
                })
                $("#listLoad").append(str+"</div></div>");

            })
            expePic();//控制图片大小
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
        var dataLength = $(".list").length;
        console.log(dataLength+", "+dataLength);
        if (dataLength >= recordCount)//还没有达到最大的总数后，可以翻页
        {
            $(".weui-infinite-scroll").html("暂无数据");
            return;
        }
        pageNo++;
        listBook();
        $(".weui-infinite-scroll").css("display", "none");
        loading = false;
    }, 1000);
});

