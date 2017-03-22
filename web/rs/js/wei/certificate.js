var pageNo =0;
$(function () {
    loadData();
})

function loadData() {
    $.getJSON(basePath + "wei/st/cert/datagrid?pageNo=" + pageNo, function (res) {
        if (res.success) {
            var data = res.obj.datas;//列表
            $.each(data, function (i, item) {
               $(".boxes").append('<a href="'+basePath+'wei/st/cert/to/msg/view/'+item.id+'">'+
                    '<div class="box">'+
                    ' <div class="certificate">'+
                    ' <img src="'+basePath + 'fileupload/thumb/'+ item.pic+'">'+
                    ' <p>'+item.remark+'</p>'+
                    ' </div>'+
                    ' <div class="border-bottom-shadow"></div>'+
                    ' <div class="border-bottom"></div>'+
                    ' </div>'+
                    ' </a>');
            })

        }
    });
}