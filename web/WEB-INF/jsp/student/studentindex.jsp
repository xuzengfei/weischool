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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 学生管理 <span class="c-gray en">&gt;</span>学生资料  <a class="btn btn-success radius r mr-20"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
    <tr>
        <td width="150" class="va-t">
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
            enable: false
        },
        view: {
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
            beforeClick: function (treeId, treeNode) {
                if(treeNode.id==0)
                return false;

                $("#testIframe").attr("src", basePath + "web/manager/student/grade/" + treeNode.id+"?cpName="+treeNode.name);
                return true;
            }
        }
    };

    $(document).ready(function () {
        loadTree();
        demoIframe = $("#testIframe");
    });

    function loadTree() {
        $.ajax({
            type: "GET",
            url: basePath + 'web/manager/campus/tree',
            datatype: "json",
            async: false,
            success: function (data) {
                var t = $("#tree");
                t = $.fn.zTree.init(t, setting, data);
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
</script>
</body>
</html>