<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <LINK rel="Bookmark" href="/favicon.ico">
    <LINK rel="Shortcut Icon" href="/favicon.ico"/>
    <t:base type="H-ui.css,icon.css,tree.css,jquery,layer,laypage,wdate,tools,tree,treesuper"></t:base>
    <title>管理员列表</title>
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
        class="c-gray en">&gt;</span> 系统设置 <a class="btn btn-success radius r mr-20"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
    <tr>
        <td width="200" class="va-t">
            <ul id="tree" class="ztree"></ul>
        </td>
        <td class="va-t">
            <IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100% height=390px
                    SRC=""></IFRAME>
        </td>
    </tr>
</table>
<script type="text/javascript">
    var basePath = '${ pageContext.request.contextPath }/';
    $(function () {
        $("#testIframe").css("height", $(document).height() - 10);
    })

    var demoIframe;
    var oldname;//修改前的结点名字，是为了防止修改后结点名字相同还要提交到后台去
    var setting = {
        edit: {
            showRemoveBtn: showRemoveBtn,
            enable: true
        },
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeRename: beforeRename,
            onRename:onRename,
            beforeRemove: beforeRemove,
            onRemove: onRemove,
            beforeClick: function (treeId, treeNode) {
                $("#testIframe").attr("src", basePath + "auth/module/to/edit/" + treeNode.id + "?pname=" + treeNode.getParentNode().name);
                return true;
            }
        }
    };

    $(document).ready(function () {
        loadTree();
        demoIframe = $("#testIframe");
        /*	var zTree = $.fn.zTree.getZTreeObj("tree");
         zTree.selectNode(zTree.getNodeByParam("id", 0));*/
    });

    function loadTree() {
        $.ajax({
            type: "GET",
            url: basePath + 'auth/module/tree',
            datatype: "json",
            async: false,
            success: function (data) {
                var t = $("#tree");
                t = $.fn.zTree.init(t, setting, data);
                t.setting.edit.showRenameBtn = true;
            },
            //调用出错执行的函数
            error: function () {
                alert("网络异常");
            }
        });
    }

    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                maxH = Math.max(bodyH, htmlH),
                minH = Math.min(bodyH, htmlH),
                h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }

    /**
     *重命名
     * 1.如果新的名字长度为0或者大于十个字,则返回false
     * @param event
     * @param treeId
     * @param treeNode
     * @param isCancel
     * @returns {*}
     */
    function beforeRename(treeId, treeNode, newName, isCancel) {
        if(newName.length==0||newName.length>10)
         return false;
         oldname =treeNode.name;
        return true;
    }
    /**
     *是否显示删除按钮
     * 1.如果是非叶子结点不可以删除
     * 2.如果是子节点而且canDel为true的时候，则出现按钮
     * @param treeId
     * @param treeNode
     * @returns {boolean}
     */
    function showRemoveBtn(treeId, treeNode) {
        if (typeof (treeNode.children) != "undefined") {
            return false;
        }

        return treeNode.canDel;

    }
    /**
    *显示添加按钮
    * @param treeId
    * @param treeNode
     */
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);
        if (btn) btn.bind("click", function () {
            $("#testIframe").attr("src", basePath + "auth/module/to/add/" + treeNode.id + "?pname=" + treeNode.name+"&floor="+treeNode.floor);
            return false;
        });
    }
    ;
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    };
    function onRename(event, treeId, treeNode, isCancel) {
        if(treeNode.name==oldname){
            return ;
        }
        $.post(basePath + "auth/module/edit", { id: treeNode.id, name: treeNode.name },
                function(response){
                  if(!response.success){
                      layer.msg(response.msg, {shift: 6});
                  }
                });

    }
    function onRemove(event, treeId, treeNode) {

    }
    function beforeRemove(treeId, treeNode) {
        layer.confirm("是否删除结点【"+treeNode.name+"?】", {
            btn: ['是','否'] //按钮
        }, function(){
            $.post(basePath + "auth/module/edit", { id: treeNode.id, delFlag:1 },
                    function(response){
                        if(!response.success){
                            layer.msg(response.msg, {shift: 6});
                        }else{
                            layer.msg(response.msg);
                            loadTree();
                        }
                    });

        }, function(){
            layer.msg('决定好再操作', {shift: 6});
        });

        return false;
    }
</script>
</body>
</html>