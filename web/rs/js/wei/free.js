$(function () {
    getData(); //列出
})
var amount = 0;
/**
 * 列出用户
 */
function getData() {
    $.getJSON(basePath + "wei/st/pay/grade/" + gradeId + "/costtpl/" + cosId + "/free/data", function (res) {
        if (res.success) {
            var data = res.attributes;
            amount =parseInt(data.amount);
            $(".d2").text(data.gradeName);
            $(".td4").text(data.classNum + "课时");
            $(".td6").text("￥" + data.amount);
            $(".trueFee_p").text("￥" + amount);
            $.each(data.coupons, function (i, item) {
                $(".fee select").append("<option  value=\"" + item.id + "\" _num=\""+item.useRule+"\" >" + item.useRule + "元现金券</option>");
            })
        }
    });
}
function chooseConpus(elemnt) {
    var  conpus =elemnt.value;
    if(conpus!="-1"){
       var nowAmo = amount- parseInt($(".fee select").find("option:selected").attr("_num"));
        $(".trueFee_p").text("￥" + nowAmo);
    }else{
        $(".trueFee_p").text("￥" + amount);
    }
}