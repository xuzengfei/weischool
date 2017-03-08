package com.bugframework.common.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bugframework.auth.pojo.UserAccount;
 

public class ResourceUtil {
	/**
	 * 用户Session 的名称全局
	 */
	public final static String USER_SESSION = "sysuser";
	public final static String LEFT_MENU1_SESSION = "left_menu1";
	public final static String LEFT_MENU2_SESSION = "left_menu2";
	public final static String LEFT_MENU3_SESSION = "left_menu3";
	public final static String ADD_SUCCESS = "添加成功！";
	public final static String UPD_SUCCESS = "更新成功！";
	public final static String DEL_SUCCESS = "删除成功！";
	public final static String ENABLE_SUCCESS = "启用成功！";
	public final static String DISABLE_SUCCESS = "禁用成功！";

	public static String getRequestPath(HttpServletRequest request) {
		String requestPath =null;
		/*if(request.getQueryString()!=null) {
			 requestPath = request.getRequestURI() + "?"
					+ request.getQueryString();
			if (requestPath.indexOf("&") > -1) {
				requestPath = requestPath.substring(0, requestPath.indexOf("&"));
			}
		}else{
			requestPath = request.getRequestURI();
		}*/
		requestPath = request.getRequestURI();
		requestPath = requestPath.substring(request.getContextPath().length() + 1);
		return requestPath;
	}

	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst(
				"WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator)
				.replaceAll("%20", " ");
		return resultPath;
	}

	public static String getPorjectPath() {
		String nowpath = System.getProperty("user.dir");
		String tempdir = nowpath.replace("bin", "webapps");
		tempdir = tempdir + "\\";
		return tempdir;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getParameter(String field) {
		HttpServletRequest request = getRequest();
		return request.getParameter(field);
	}

	/**
	 * 获得HttpServletRequest
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获得 HttpSession
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	/**
	 * 获得session中的系统用户
	 * 
	 * @return SysUser
	 */
	public static UserAccount getUserSession() {

		HttpSession session = getSession();
		if (session != null) {
			UserAccount user = (UserAccount) session
					.getAttribute(USER_SESSION);
			return user;
		} else {
			return null;
		}
	}

	/**
	 * 获得IP
	 * 
	 * @return
	 */
	public static String getIpAddr() {
		String ip = getRequest().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		return ip;
	}

	public static String basePath(HttpServletRequest request){
		return request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() +  request.getContextPath() + "/";
	}
	 

}