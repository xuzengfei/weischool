package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * datagrid的列属性标签
 *
 * @author 许增飞
 */
public class DataGridColumnTag extends TagSupport {
    /**
     * 字段标题
     */
    private String title;
    /**
     * 字段值
     */
    private String field;
    /**
     * 字段宽度
     */
    private String width;
    /**
     * 可选值有left,right,center
     */
    private String align;
    /**
     * 是否排序，默认不排序
     */
    private boolean sortable = false;
    /**
     * 是否显示选择框，默认不显示
     */
    private boolean checkbox = false;
    /**
     * 时间转换器
     */
    private String formatter;
    /**
     * 列隐藏(隐藏:true)
     */
    private boolean hidden = false;
    /**
     * 字段值替换(例子 : 男_0,女_1)
     */
    private String replace;
    /**
     * 是否显示图片字段，默认 false
     */
    private boolean image;
    /**
     * 图片宽度
     */
    private String imgWidth;
    /**
     * 图片高度
     */
    private String imgHeight;
    /**
     * 是否为添加查询列
     */
    private boolean query = false;
    /**
     * 列的样式
     */
    private String tdClass;
    /**
     * 自定义函数名称
     */
    private String funname;
    /**
     * 列内容样式
     */
    private String icon;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public void setQuery(boolean query) {
        this.query = query;
    }

    public void setTdClass(String tdClass) {
        this.tdClass = tdClass;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int doEndTag() throws JspException {
        Tag t = findAncestorWithClass(this, DataGridTag.class);
        DataGridTag parent = (DataGridTag) t;
        DataGridColumn column = new DataGridColumn(title, field, width, align, sortable, checkbox, formatter, hidden, replace, image, imgWidth, imgHeight, query, tdClass, funname, icon);
        parent.setDataGridColumns(column);
        return EVAL_PAGE;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
