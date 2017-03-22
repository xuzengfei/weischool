package com.ws.service.weixin;

import com.bugframework.common.utility.ResourceUtil;
import com.souvc.weixin.pojo.WeixinOauth2Token;
import com.souvc.weixin.util.AdvancedUtil;
import com.souvc.weixin.util.WeiXinConfig;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.student.StudentOpenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 2017/2/15.
 */
@Service(value = "weixinLoginService")
public class WeixinLoginServiceImpl implements WeixinLoginService {
    @Autowired
    private StudentOpenIdService studentOpenIdService;
    @Override
    public void toCodeURl(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            String appid = WeiXinConfig.getValue("appid");//获得appID
            String redirect_uri = WeiXinConfig.getValue("redirect_uri");//获得回调地址
            String getCodeURL = WeiXinConfig.getValue("getCodeURL");//去微信第三方平台请求地址，返回code参数
           // String redirect  =java.net.URLEncoder.encode(ResourceUtil.basePath(request)+redirect_uri,"utf-8");
            String redirect  = ResourceUtil.basePath(request)+redirect_uri ;
            response.sendRedirect(new AdvancedUtil().getAuthorizeURL(appid, redirect, getCodeURL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getOpenId(HttpServletRequest request) {
        try {
            String code = request.getParameter("code");
            if (code != null && !"".equals(code)) {
                String appid = WeiXinConfig.getValue("appid");//获得appID
                String getOpenURL = WeiXinConfig.getValue("getOpenURL");//去微信第三方平台请求地址，返回openId等信息
                String secret = WeiXinConfig.getValue("secret");//获得appID
                // 获取网页授权access_token
                WeixinOauth2Token weixinOauth2Token = new AdvancedUtil().getOauth2AccessToken(appid, secret, code, getOpenURL);
                // 用户标识
                String openId = weixinOauth2Token.getOpenId();
                return openId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isOpenIdExist(String openId) {
        if (openId != null && !"".equals(openId)) {
       /*     int flag = this.weixinLoginDao.isOpenIdExist(openId);
            if (flag > 0) {
                return true;
            }*/
            return true;
        }
        return false;
    }

    @Override
    public StudentOpenId getByOpenid(String  openId) {
        if (openId==null)
            return null;
        return  studentOpenIdService.get(openId);
    }


}
