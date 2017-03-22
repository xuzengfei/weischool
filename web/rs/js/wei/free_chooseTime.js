$(function () {
    listcosttpl(); //列出
})

/**
 * 列出用户
 */
function listcosttpl() {
    $.getJSON(basePath + "wei/st/pay/grade/" + gradeId + "/costtpls/datas", function (res) {
        if (res.success) {
            var data = res.obj;
            $.each(data, function (i, item) {
                $(".container").append("<a href=\"javascript:goto('" + item.id + "')\"> <div class=\"timeChoose\"> <div> <h3>" + item.classNum + "课时</h3> <h4>￥" + item.amount + "</h4> <h5>" + item.remark + "</h5> </div> </div> </a>");
            })
        }
    });
}
function goto(id) {
    window.location.href = basePath + "wei/st/pay/grade/" + gradeId + "/costtpl/" + id+"/free";
}