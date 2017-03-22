package com.bugframework.common.tag;

import com.bugframework.common.utility.DataUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据列表的列字段属性
 *
 * @author 许增飞
 */
public class DataGridColumn {
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
     * 数据对其方式,默认左对齐，可选值有left,right,center
     */
    private String align = "left";
    /**
     * 是否排序，默认不排序
     */
    private boolean sortable = false;
    /**
     * 是否显示选择框，默认不显示
     */
    private boolean checkbox = false;
    /**
     * 自定义函数名称
     */
    private String formatter;
    /**
     * 列隐藏(隐藏:true)
     */
    private boolean hidden = false;
    /**
     * 字段值替换(例子 : 0_男,1_女)
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
    private Map<String, String> replaceMap = new HashMap<String, String>();//将replace字段的拆分，存放到map中，便于查询

    public DataGridColumn() {

    }

    public DataGridColumn(String title, String field, String width,
                          String align, boolean sortable, boolean checkbox, String formatter,
                          boolean hidden, String replace, boolean image, String imgWidth,
                          String imgHeight, boolean query, String tdClass, String funname, String icon) {
        this.title = title;
        this.field = field;
        this.width = width;
        this.align = align;
        this.sortable = sortable;
        this.checkbox = checkbox;
        this.formatter = formatter;
        this.hidden = hidden;
        this.replace = replace;
        this.image = image;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.query = query;
        this.tdClass = tdClass;
        this.funname = funname;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getAlign() {
        if (align == null)
            align = "center";
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        if (DataUtils.isStrNotEmpty(replace)) {
            String[] replaceArray = replace.split(",");
            for (int i = 0; i < replaceArray.length; i++) {
                String[] ck = replaceArray[i].split("_");
                replaceMap.put(ck[0], ck[1]);
            }
        }
        this.replace = replace;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public boolean isQuery() {
        return query;
    }

    public void setQuery(boolean query) {
        this.query = query;
    }


    public Map<String, String> getReplaceMap() {
        return replaceMap;
    }

    public void setReplaceMap(Map<String, String> replaceMap) {
        this.replaceMap = replaceMap;
    }

    public String getTdClass() {
        return tdClass;
    }

    public void setTdClass(String tdClass) {
        this.tdClass = tdClass;
    }

    public String getFunname() {
        return funname;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


}
