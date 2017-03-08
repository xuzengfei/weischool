package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 列表里面的自定义按钮
 * @author 许增飞
 *
 */
public class DataGridFunOptTag  extends TagSupport{
	/**
	 * 按钮名称
	 */
	protected String title;
	/**
	 * 判断链接是否显示的表达式
	 */
	private String exp;
	/**
	 * 自定义函数名称
	 */
	private String funname;
	/**
	 * 按钮样式
	 */
	private String icon;
	
	@Override
	public int doEndTag() throws JspException {
		 
		 Tag t = findAncestorWithClass(this, DataGridTag.class);
		 DataGridTag parent = (DataGridTag) t;
		 DataGridButton button = new DataGridButton();
		 button.setExp(exp);
		 button.setFunname(funname);
		 button.setTitle(title);
		 button.setIcon(icon);
		 parent.setCzBtn(button);
		 return EVAL_PAGE;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
	/**
	 * 自定义一些按钮的样式
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
