package com.ws.service.weixin;

import com.ws.pojo.student.StudentOpenId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/2/15.
 */
public interface WeixinLoginService {
    /**
     * 获得code的url,通过response跳转到回调地址，并且在回调地址上带上code的参数
     * @param request：request参数
     * @param response：response 参数
     * @param to 跳转到老师(tc)或者学生(st)验证界面
     * @return 直接跳转，不需要返回值
     */
    void toCodeURl(HttpServletRequest request, HttpServletResponse response,String to);

    /**
     * 获得用户唯一标识
     * @param request ：response 参数
     * @return 返回openId,如果为空则返回null
     */
    String getOpenId(HttpServletRequest request);

    /**
     * 判断openId是否已经存在了
     * @param openId：用户唯一标识
     * @return 返回成功或者失败
     */
    boolean isOpenIdExist(String openId);

    StudentOpenId getByOpenid(String openId);
}
