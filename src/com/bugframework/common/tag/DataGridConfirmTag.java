package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.utility.DataUtils;
/**
 * 定制一些询问的按钮操作，如：启用禁用操作
 * @author 许增飞
 *
 */
public class DataGridConfirmTag extends TagSupport{
	/**
	 * 按钮名称
	 */
	private String title;
	/**
	 * 请求的地址
	 */
	private String url;
	/**
	 * 提醒信息
	 */
	private String message;
	/**
	 * 拓展功能，如控制显示或者隐藏
	 */
	private String exp;
	/**
	 * 按钮图标
	 */
	private String icon;
	/**
	 * 提交方式GET/POST/DELETE/PUT
	 */
	private String post;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	@Override
	public int doEndTag() throws JspException {
		 Tag t = findAncestorWithClass(this, DataGridTag.class);
		 DataGridTag parent = (DataGridTag) t;
		 DataGridButton button = new DataGridButton();
		 button.setExp(exp);
		 button.setUrl(url);
		 button.setTitle(title);
		 button.setIcon(icon);
		 StringBuffer sb = new StringBuffer();
		 if(DataUtils.isStrNotEmpty(url)){
			 sb.append("datagridConfirm('"+url+"','"+this.message+"','"+this.post+"')");
			 button.setFunname(sb.toString());
		 }
		 parent.setCzBtn(button);
		return EVAL_PAGE;
	}

}
