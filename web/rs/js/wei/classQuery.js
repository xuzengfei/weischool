var pageNo =1;
var recordCount =0;//总记录数
var pageSize=2;
var requestData={};
$(function(){
    loadPic(); //加载头像
    listCourse(requestData); //列出用户
    $("#btn").click(function () { //提交按钮点击查询
        search();
    })
    $(".close-popup").click(function () {//点击关闭，初始查询框
        document.getElementById('half').reset();
    })
    loadTopData();
})
/**
 * 初始化
 */
function init() {
    $(".list-group-item").remove();//清空列表
    pageNo=1;
    $(".weui-infinite-scroll").html('<div class="infinite-preloader"></div>正在加载').css("display","none");
    loading=false;
}

/**
 * 列出用户
 */
function  listCourse(rdata) {
    $.getJSON(basePath+"wei/st/course/grade/register/datagrid?pageNo="+pageNo+"&gradeId="+moduleId,rdata, function(res){
        if(res.success){
            var data = res.obj.datas;//列表
            recordCount =res.obj.recordCount;
            $.each(data,function(i,item){
                var status =item.status;//1--准时 2--请假 -1旷课
                var satusVal ="";
                switch (status){
                    case 1:statusVal="准";break;
                    case 2:statusVal="请";break;
                    case 3:statusVal="旷";break;
                    default:statusVal="迟";break
                }
                $("#listLoad1").append(
                    "<div class='list-group-item'>" +
                    "<p class='date'>"+$.myTime.UnixToDate(item.ct, "yyyy-MM-dd")+"</p>" +
                    "<p class='teacher'>授课老师：</p>" +
                    "<p class='teacherVal'>"+item.tcName+"</p>" +
                    "<p class='state'>"+statusVal+"</p></div>");

            })
            if(pageNo==1&&$(".list-group-item").length>0){//获得最新签到时间，显示到头部
               $(".classTop h5").html($(".list-group-item").eq(0).children(".date").html());
            }
            setColor();//设置color
        }
    });
}
/**
 * 瀑布流
 * @type {boolean}
 */
var loading = false;
$(document.body).infinite().on("infinite", function() {
    $(".weui-infinite-scroll").css("display","");
    if(loading) return;
    loading = true;
    setTimeout(function() {
        var dataLength = $(".list-group-item").length;
        if(dataLength>=recordCount)//还没有达到最大的总数后，可以翻页
        {
            $(".weui-infinite-scroll").html("暂无数据");
            return;
        }
        pageNo++;
        listCourse(requestData);
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
        requestData["createTime_ST"] =strTotimestamp(start+" 00:00:00");
        requestData["createTime_ED"] =strTotimestamp(end+" 23:59:59");
    }
    init();//初始化后查询
    listCourse(requestData);
}
//加载头像
function loadPic(){
    loadStPic(basePath);
}
//加载头部信息
function loadTopData() {
    $.getJSON(basePath+"wei/st/course/rest/class/"+sgId,function(res){
        if(res.success){
            var data =res.obj;
            $(".classTop h3").html(data.gradeName+"上课签到");
            $(".classTop h2").html(data.restClass);
        }
    })
}