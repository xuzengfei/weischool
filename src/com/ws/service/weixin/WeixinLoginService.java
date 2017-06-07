package com.ws.service.weixin;

import com.ws.pojo.student.StudentOpenId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by admin on 2017/2/15.
 */
public interface WeixinLoginService {
    /**
     * 获得code的url,通过response跳转到回调地址，并且在回调地址上带上code的参数
     *
     * @param request：request参数
     * @param response：response 参数
     * @param to                跳转到老师(tc)或者学生(st)验证界面
     * @return 直接跳转，不需要返回值
     */
    void toCodeURl(HttpServletRequest request, HttpServletResponse response, String to);

    /**
     * 获得用户唯一标识
     *
     * @param request ：response 参数
     * @return 返回openId, 如果为空则返回null
     */
    String getOpenId(HttpServletRequest request);

    /**
     * 判断openId是否已经存在了
     *
     * @param openId：用户唯一标识
     * @return 返回成功或者失败
     */
    boolean isOpenIdExist(String openId);

    StudentOpenId getByOpenid(String openId);

    /**
     * 获得微信jssdk配置参数值
     *
     * @param url 当前界面地址
     * @return Map对象：
     * appId: 必填，公众号的唯一标识
     * timestamp: 必填，生成签名的时间戳
     * nonceStr: 必填，生成签名的随机串
     * signature:必填，签名
     * @throws Exception
     */
    Map<String, String> getWeiConfig(String url) throws Exception;
}
