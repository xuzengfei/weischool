$(function () {
    $("#uploadFile").click(function () {
        $(".webuploader-element-invisible").trigger("click");
    })
    loadFile();
});
// 文件上传
jQuery(function () {

        fileSingleSizeLimit = 5242880,
        fileSingleSizeLimit1 = '5M',
        fileType = 'jpg,jpeg,bmp,png'
    mimeTypes = '.jpg,.jpeg,.bmp,.png';
    var $ = jQuery,
        uploader;
    uploader = WebUploader.create({
        // 自动上传。
        auto: true,
        // 不压缩image
        resize: false,
        // 文件接收服务端。
        server: uploadcontroller,
        fileSingleSizeLimit: fileSingleSizeLimit,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        compress: false,// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        pick: {
            id: '#filePicker',
            multiple: false
        },
        timeout: 5 * 60 * 1000,// 5分钟
        accept: {
            extensions: fileType,
            mimeTypes: mimeTypes
        }
    }).on("startUpload", function () {//开始上传的时候清除旧的图片
        if ($("input[name=pics]").length > 0) {
            $.each($("input[name=pics]"), function (i, item) {
                del($(this).val());
            })
        }
    }).on('fileQueued', function (file) {

    }).on('uploadProgress', function (file, percentage) {

    }).on('uploadSuccess', function (file, response) {
        if (response.success) {
            parent.layer.msg(response.msg);
            try {
                uploadSuccess(response.obj.id, response.obj.path);
            } catch (e) {
                parent.layer.msg('找不到上传成功 uploadSuccess(response) 回调方法！');
            }
        } else {
            parent.layer.msg(response.msg);
        }
    }).on('uploadError', function (file, reason) {
        parent.layer.msg('Upload failed!');
    }).on('error', function (type) {
        var errorMsg = "";
        if (type == "Q_EXCEED_NUM_LIMIT") {
            errorMsg = "Exceed the maximum limit amount 1";
        } else if (type == "Q_TYPE_DENIED") {
            errorMsg = "Does not meet the type";
        } else if (type == "F_EXCEED_SIZE") {
            errorMsg = "Exceed the maximum limit size " + fileSingleSizeLimit1;
        }
        parent.layer.msg(errorMsg);
    });
});
/**界面显示**/
function uploadSuccess(id, path) {
    try {
        $("#uploadFile").attr("src", basePath + "fileupload/" + path);
        $("input[name=pics]").remove();
        $("#uploadFile").before("<input type='hidden' id='" + id + "' value='" + id + "' name='pics' >");
    } catch (e) {
        alert(e);
    }
}
function loadFile(){
    if(typeof(moduleId)=="undefined"||moduleId=="null"||moduleId=="")
        return;
    $.getJSON( basePath+'web/manager/attach/list/'+moduleId,function(response){
        if(response.success){
            $.each(response.obj,function(i,item){
                uploadSuccess(item.id,item.path);
            })
        }

    });
}
function del(id) {
    $.ajax({
        type: "DELETE",
        url: basePath + "web/manager/attach/" + id,
        datatype: "json",
        async: false
    });
}