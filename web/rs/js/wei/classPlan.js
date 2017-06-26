//控制课程表随机颜色块
$(function(){
    var color=new Array("#1bb9e0","#ff7e50","#50ffb7","#ff5050");
    $(".classDetail").each(function(){
        var c=Math.floor(Math.random()*3);
        $(this).css("background-color",color[c]);
    });
});

$(function(){
    $(".classDetail").each(function(){
        var s=$(this).find(".start").text();
        var o=$(this).find(".end").text();
        var ss=parseInt(s.split(":"));
        var oo=parseInt(o.split(":"));
        var h=$(this).height();
        if(oo-ss>=3){
            $(this).css("height",3/2*h+"px");
        }
        //if(ss>12){
        //    $(this).css("top","60%");
        //}
    });
});
$(function () {
   $("#before").click(function () {
      $(".dayNum").each(function () {
          $(this).fadeOut(200);
          $(this).text(parseInt($(this).text())-7);
          $(this).fadeIn(200);
      })
   });
    $("#after").click(function () {
        $(".dayNum").each(function () {
            $(this).fadeOut(200);
            $(this).text(parseInt($(this).text())+7);
            $(this).fadeIn(200);
        })
    });
});
