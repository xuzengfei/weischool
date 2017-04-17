$(function(){
    //列出用户
    listCourse();
    //点击课程名称跳转
    $(".container").on("click","a", function() {
         window.location.href=basePath+"wei/st/course/to/choose/grade/"+$(this).attr("id")+"/studentgrade/"+$(this).attr("sgId");
    });
})
/**
 * 列出用户
 */
function  listCourse() {
    $.getJSON(basePath+"wei/st/course/list/grade", function(res){
        if(res.success){
            $.each(res.obj,function(i,item){
                $(".container").append('<a href="javascript:void(0);" name="className" id="'+item.id+'" sgId="'+item.sgId+'" ><button>'+item.name+'</button></a>');
            })

        }
    });
}