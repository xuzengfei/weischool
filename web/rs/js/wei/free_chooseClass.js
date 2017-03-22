$(function () {
    listFree(); //列出
})

/**
 * 列出用户
 */
function listFree() {
    $.getJSON(basePath + "wei/st/course/list/cp/grade", function (res) {
        if (res.success) {
            var data = res.obj;
            $.each(data, function (i, item) {
                $(".container").append("<a href=\"javascript:goto('" + item.id + "')\"><button>" + item.name + "</button></a>");
            })
        }
    });
}

function goto(id) {
    window.location.href = basePath + "wei/st/pay/grade/"+id+"/costtpls";
}