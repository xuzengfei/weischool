package com.ws.controller.wei.tc.interceptor;

import com.bugframework.common.utility.ResourceUtil;
import com.ws.controller.wei.tc.common.WeiTcLoginUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2017/2/14.
 */
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

        if (WeiTcLoginUtils.getTeacherSession() != null) {
            return true;
        }

        if (excludeUrls.contains(requestPath) || excludeUrls.contains(url + "?*")) {
            return true;
        } else {

            String requestType = ResourceUtil.getRequest().getHeader("X-Requested-With");
            if (requestType == null) {//说明是非ajax提交
                response.sendRedirect(ResourceUtil.basePath(request) + "wei/tc/login/code");
            }
            return false;


        }

    }
}
