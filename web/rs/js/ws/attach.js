$(function(){
    $("#uploadFile").click(function(){
    $(".webuploader-element-invisible").trigger("click");
    })
    $.Huihover(".portfolio-area li");
    loadFile();
});

// 文件上传
jQuery(function () {
    var size = 5,
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
        server:uploadcontroller,
        fileSingleSizeLimit: fileSingleSizeLimit,
        fileNumLimit: size,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        compress: false,// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        pick: '#filePicker',
        timeout: 5 * 60 * 1000,// 5分钟
        accept: {
            extensions: fileType,
            mimeTypes: mimeTypes
        }
    }).on('fileQueued', function (file) {

    }).on('uploadProgress', function (file, percentage) {

    }).on('uploadSuccess', function (file, response) {
        if (response.success) {
            parent.layer.msg(response.msg);
            try{
                uploadSuccess(response.obj.id,response.obj.path);
            }catch (e){
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
            errorMsg = "Exceed the maximum limit amount " + size;
        } else if (type == "Q_TYPE_DENIED") {
            errorMsg = "Does not meet the type";
        } else if (type == "F_EXCEED_SIZE") {
            errorMsg = "Exceed the maximum limit size " + fileSingleSizeLimit1;
        }
        parent.layer.msg(errorMsg);
    });
});

/**界面显示**/
function uploadSuccess(id,path){
    try{
        $(".uploadFile").before("<li class=\"item\">"+
            " <div class=\"portfoliobox\">"+
            "<i class=\"Hui-iconfont checkbox\" onclick=\"del(this,'"+id+"')\">&#xe6e2;</i>"+
            " <div class=\"picbox\"><a href=\"javascript:viewByUrl('"+basePath+"fileupload/"+path+"',500,500)\" data-lightbox=\"gallery\" data-title=\"\"><img src=\""+basePath+"fileupload/"+path+"\"></a></div>"+
            "  </div><input type='hidden' id='"+id+"' value='"+id+"' name='pics' >"+
            " </li>");
    }catch (e){
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

function del(ctrl,id){
    layer.confirm("是否删除图片？", {
        btn: ['是','否'], //按钮
        shade: false //不显示遮罩
    }, function(){
        $.ajax({
            type:"DELETE",
            url:basePath+"web/manager/attach/"+id,
            datatype: "json",
            async:false,
            success:function(data){
                if(data.success){
                    $(ctrl).parent().parent().remove();
                }
                layer.msg(data.msg, {shift: 6});
            }
        });
    }, function(){
        layer.msg('决定好再操作', {shift: 6});
    });
}