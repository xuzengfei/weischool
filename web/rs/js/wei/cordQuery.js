var pageNo =1;
var recordCount =0;//总记录数
var requestData={};
$(function(){
    $(".img-circle").nextAll().css("display","none");
    $(".classTop").append('<p style="display: inline-block" id="nulldata">暂无数据</p>');
    loadPic(); //加载头像
    listCord(requestData); //列出用户
    $("#btn").click(function () { //提交按钮点击查询
        search();
    })
    $(".close-popup").click(function () {//点击关闭，初始查询框
        document.getElementById('half').reset();
    })

})
/**
 * 初始化
 */
function init() {
    $(".list-group-item").remove();//清空列表
    $(".weui-infinite-scroll").html('<div class="infinite-preloader"></div>正在加载').css("display","none");
    loading=false;
}

/**
 * 列出用户
 */
function  listCord(rdata) {
    $.getJSON(basePath+"wei/st/exam/datagrid?pageNo="+pageNo,rdata, function(res){
        if(res.success){
            var data = res.obj.datas;//列表
            recordCount =res.obj.recordCount;
            $.each(data,function(i,item){
                if (pageNo == 1 && i == 0){
                  $(".img-circle").nextAll().css("display","");
                  $("#nulldata").remove();
                  $(".classTop h3").html(item.title);
                  $(".classTop h5").html($.myTime.UnixToDate(item.exTime, "yyyy-MM-dd"));
                  $(".classTop h2").html(item.status==1?"已通过":"未通过");
                  if(item.status==0)
                      $(".classTop h2").css("background-color", "#f26d3e");
                  else
                      $(".classTop h2").css("background-color", " #1bb9e0");
                } else
                $("#listLoad").append(
                    "<div class='list-group-item'>"+
                    "<p class='itemTitle'>"+item.title+"</p>"+
                    "<p class='date'>"+$.myTime.UnixToDate(item.exTime, "yyyy-MM-dd")+"</p>"+
                    "<p class='detail'>"+item.remark+"</p>"+
                    "<p class='cord'>"+(item.status==1?"已通过":"未通过")+"</p></div>");
            })
            exameColor();//设置color
        }
    });
}
/**
 * 瀑布流
 * @type {boolean}
 */
var loading = false;
$(document.body).infinite().on("infinite", function() {
    if(loading) return;
    $(".weui-infinite-scroll").css("display","");
    loading = true;
    setTimeout(function() {
        var dataLength = $(".list-group-item").length;

        if(dataLength>=recordCount-1)//还没有达到最大的总数后，可以翻页
        {
            $(".weui-infinite-scroll").html("暂无数据");
            return;
        }
        pageNo++;
        listCord(requestData);
        $(".weui-infinite-scroll").css("display","none");
        loading = false;
    }, 1000);
});
/**
 * 查询函数
 */
function search() {
    if($("input[name=status]:checked").length>0){
        requestData["status"] =$("input[name=status]:checked").val()
    }
    var start =$("#start").val();
    var end =$("#end").val();
    if(start!=""&&end!=""){
        requestData["exTime_ST"] =strTotimestamp(start+" 00:00:00");
        requestData["exTime_ED"] =strTotimestamp(end+" 23:59:59");
    }
    init();//初始化后查询
    listCord(requestData);

}
//加载头像
function loadPic(){
    loadStPic(basePath);
}
