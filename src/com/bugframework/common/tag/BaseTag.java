package com.bugframework.common.tag;

import com.bugframework.common.exception.NotFindException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class BaseTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected String type = "default";// 加载类型
    protected String basePath = null;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            basePath = TagUtil.basePath(pageContext);
            //	basePath = this.pageContext.getServletContext().getContextPath()+ "/";
            //	basePath="http://localhost:8888/";
            StringBuffer sb = new StringBuffer();
            String types[] = type.split(",");
            for (int i = 0; i < types.length; i++) {
                if (types[i].equals("H-ui.css")) {
                    sb.append("<!--[if lt IE 9]>");
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/html5.js'></script>");
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/respond.min.js'></script>");
                    sb.append("<![endif]-->");
                    sb.append("<link href='" + basePath + "css/H-ui.min.css' rel='stylesheet' type='text/css' />");
                    sb.append("<link href='" + basePath + "css/H-ui.admin.css' rel='stylesheet' type='text/css' />");
                    continue;
                }
                if (types[i].equals("H-ui")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "js/H-ui.js' ></script>");
                    sb.append("<script type='text/javascript' src='" + basePath + "js/H-ui.admin.js' ></script>");
                    continue;
                }
                if (types[i].equals("icon.css")) {
                    sb.append("<link href='" + basePath + "lib/Hui-iconfont/1.0.1/iconfont.css' rel='stylesheet' type='text/css' />");
                    continue;
                }
                if (types[i].equals("jquery")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/jquery/1.9.1/jquery.min.js' ></script>");
                    continue;
                }
                if (types[i].equals("layer")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/layer/1.9.3/layer.js' ></script>");
                    continue;
                }
                if (types[i].equals("laypage")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/laypage/1.2/laypage.js' ></script>");
                    continue;
                }
                if (types[i].equals("wdate")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/My97DatePicker/WdatePicker.js' ></script>");
                    continue;
                }
                if (types[i].equals("tools")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "js/tools.js' ></script>");
                    continue;
                }
                if (types[i].equals("check")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/icheck/jquery.icheck.min.js' ></script>");
                    continue;
                }
                if (types[i].equals("check.css")) {
                    sb.append("<link href='" + basePath + "lib/icheck/icheck.css' rel='stylesheet' type='text/css' />");
                    continue;
                }
                if (types[i].equals("valiform")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/Validform/5.3.2/Validform.min.js' ></script>");
                    continue;
                }
                if (types[i].equals("style.css")) {
                    sb.append("<link href='" + basePath + "css/style.css' rel='stylesheet' type='text/css' />");
                    continue;
                }
                if (types[i].equals("tree.css")) {
                    sb.append("<link href='" + basePath + "lib/zTree/v3/css/zTreeStyle/zTreeStyle.css' rel='stylesheet' type='text/css' />");
                    continue;
                }
                if (types[i].equals("tree")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/zTree/v3/js/jquery.ztree.all-3.5.min.js' ></script>");
                    continue;
                }
                if (types[i].equals("treesuper")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/zTree/v3/js/jquery.ztree.core-3.5.min.js' ></script>");
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js' ></script>");
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/zTree/v3/js/jquery.ztree.exedit-3.5.min.js' ></script>");
                    continue;
                }
                if (types[i].equals("webuploader.css")) {
                    sb.append("<link href='" + basePath + "lib/webuploader/webuploader.css'  type='text/css' />");
                    continue;
                }
                if (types[i].equals("webuploader")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/webuploader/webuploader.js' ></script>");
                    continue;
                }
                if (types[i].equals("lightbox.css")) {
                    sb.append("<link href='" + basePath + "lib/lightbox2/2.8.1/css/lightbox.css' rel='stylesheet' type='text/css' />");
                    continue;
                }
                if (types[i].equals("lightbox")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "lib/lightbox2/2.8.1/js/lightbox.js' ></script>");
                    continue;
                }
                if (types[i].equals("calendar")) {
                    sb.append("<link href='" + basePath + "lib/calendar/fullcalendar.min.css' rel='stylesheet' />");
                    sb.append("<link href='" + basePath + "lib/calendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />");
                    sb.append("<script src='" + basePath + "lib/calendar/moment.min.js'></script>");
                    sb.append("<script src='" + basePath + "lib/calendar/jquery.min.js'></script>");
                    sb.append("<script src='" + basePath + "lib/calendar/jquery-ui.min.js'></script>");
                    sb.append("<script src='" + basePath + "lib/calendar/fullcalendar.min.js'></script>");
                    sb.append("<script src='" + basePath + "lib/calendar/zh-cn.js'></script>");
                    continue;
                }
                if (types[i].equals("usertools")) {
                    sb.append("<script type='text/javascript' src='" + basePath + "js/user/usertools.js' ></script>");
                    continue;
                }
                if (types[i].equals("editor")) {
                    sb.append("<script type='text/javascript'  src='" + basePath + "lib/ueditor/ueditor.config.js'></script>");
                    sb.append("<script type='text/javascript'  src='" + basePath + "lib/ueditor/ueditor.all.min.js'> </script>");
                    continue;
                }
                throw new NotFindException("无法找到" + types[i]);//自定义异常
            }
            out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFindException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
