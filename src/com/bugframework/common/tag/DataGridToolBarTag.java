package com.bugframework.common.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DataGridToolBarTag extends TagSupport{
	/**
	 * 按钮标题
	 */
	private String title;
	/**
	 * 按钮图标
	 */
	private String icon;
	/**
	 * 按钮方法
	 */
	private String funname;
	/**
	 * 按钮的样式
	 */
	private String btClass;
	/**标题*/
	public void setTitle(String title) {
		this.title = title;
	}
	 
	/**按钮图片名称或者地址*/
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**执行函数*/
	public void setFunname(String funname) {
		this.funname = funname;
	}
	
	 public void setBtClass(String btClass) {
		this.btClass = btClass;
	}

	@Override
	 public int doEndTag() throws JspException {
		 Tag t = findAncestorWithClass(this, DataGridTag.class);
		 DataGridTag parent = (DataGridTag) t;
		 DataGridButton button = new DataGridButton();
		 button.setFunname(funname);
		 button.setTitle(title);
		 button.setIcon(icon);
		 button.setBtClass(btClass);
		 parent.setToolBar(button);
		 return EVAL_PAGE;
	 }
	
}
