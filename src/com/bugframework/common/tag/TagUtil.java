package com.bugframework.common.tag;

import java.util.List;

import javax.servlet.jsp.PageContext;

import com.bugframework.common.utility.DataUtils;


public class TagUtil {

	/**
	 * 获取自定义函数名
	 * 
	 * @param functionname
	 * @return
	 */
	public static String getFunction(String functionname) {
		int index = functionname.indexOf("(");
		if (index == -1) {
			return functionname;
		} else {
			return functionname.substring(0, functionname.indexOf("("));
		}
	}

	/**
	 * 获取自定义函数的参数
	 * 
	 * @param functionname
	 * @return
	 */
	public static String getFunParams(String functionname) {
		int index = functionname.indexOf("(");
		String param = "";
		if (index != -1) {
			String testparam = functionname.substring(
					functionname.indexOf("(") + 1, functionname.length() - 1);
			if (DataUtils.isStrNotEmpty(testparam)) {
				String[] params = testparam.split(",");
				int i =0;
				for (String string : params) {
					//针对自定函数t:dgFunOpt中方法传递参数做过滤，使用'value'传值不做替换，否则替换成字段值
					if(i==0){
						param += string;
					}else{
						param += ","+string;
					}
					i++;
				}
			}
		}
		//param += "'\"+index+\"'";// 传出行索引号参数
		return param;
	}
	
	public static String setValueReplace(String str,List<String> fields){
		for(String st:fields){
			if(str.indexOf("{"+st+"}")>-1){
				str = str.replaceAll("\\{"+st+"\\}","\"+value."+st+"+\"");
			}
		}
		return str;
	}
	public  static  String  basePath(PageContext pageContext){
		String path =pageContext.getServletContext().getContextPath();
		String basePath = pageContext.getRequest().getScheme()+"://"+pageContext.getRequest().getServerName()+path+"/rs/";
		return basePath;
	}
	public  static String uplaodPath(PageContext pageContext){
		String path =pageContext.getServletContext().getContextPath();
		String filePath = pageContext.getRequest().getScheme()+"://"+pageContext.getRequest().getServerName()+path+"/file/";
		return filePath;
	}
}
