package com.bugframework.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.utility.ResourceUtil;

public class Interceptor implements HandlerInterceptor {
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();// /包名/请求路径
		String contextPath = request.getContextPath();// /包名
		String url = requestUri.substring(contextPath.length()); // /请求路径
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
 
		if (excludeUrls.contains(requestPath)
				|| excludeUrls.contains(url + "?*")) {
			return true;
		} else {
			if (ResourceUtil.getUserSession() != null) {
				return true;
			} else {
				response.sendRedirect(ResourceUtil.basePath(request) + "auth/login");
				return false;
			}

		}

	}

}
