package com.ws.service.weixin;

import com.bugframework.common.utility.IdUtil;
import com.souvc.weixin.pojo.WeixinOauth2Token;
import com.souvc.weixin.util.AdvancedUtil;
import com.souvc.weixin.util.CommonUtil;
import com.souvc.weixin.util.WeiXinConfig;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.student.StudentOpenIdService;
import com.ws.util.Global;
import com.ws.util.WeiSign;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/15.
 */
@Service(value = "weixinLoginService")
public class WeixinLoginServiceImpl implements WeixinLoginService {
    @Autowired
    private StudentOpenIdService studentOpenIdService;

    @Override
    public void toCodeURl(HttpServletRequest request, HttpServletResponse response, String to) {
        response.setContentType("text/html;charset=utf-8");
        try {
            String appid = WeiXinConfig.getValue("appid");//获得appID
            String redirect_uri = WeiXinConfig.getValue(to + "_redirect_uri");//获得回调地址
            String getCodeURL = WeiXinConfig.getValue("getCodeURL");//去微信第三方平台请求地址，返回code参数
            // String redirect  =java.net.URLEncoder.encode(ResourceUtil.basePath(request)+redirect_uri,"utf-8");
            // String redirect  = ResourceUtil.basePath(request)+redirect_uri ;
            response.sendRedirect(new AdvancedUtil().getAuthorizeURL(appid, redirect_uri, getCodeURL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getOpenId(HttpServletRequest request) {
        try {
            String code = request.getParameter("code");
            if (code != null && !"".equals(code)) {
                String appId = WeiXinConfig.getValue("appid");//获得appID
                String getOpenURL = WeiXinConfig.getValue("getOpenURL");//去微信第三方平台请求地址，返回openId等信息
                String secret = WeiXinConfig.getValue("secret");//获得appID
                // 获取网页授权access_token
                WeixinOauth2Token weixinOauth2Token = new AdvancedUtil().getOauth2AccessToken(appId, secret, code, getOpenURL);
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
    public StudentOpenId getByOpenId(String openId) {
        if (openId == null)
            return null;
        return studentOpenIdService.get(openId);
    }

    @Override
    public List<StudentOpenId> getListByOpenId(String openId) {
        if (openId == null)
            return null;
        return studentOpenIdService.getListByOpenId(openId);
    }

    @Override
    public Map<String, String> getWeiConfig(String url) throws Exception {
        String appId = WeiXinConfig.getValue("appid");//获得appID
        if (Global.getJsapiTicketExpires() <System.currentTimeMillis())
            setJsapiTicket();
        return WeiSign.sign(Global.getJsapiTicket(), url, IdUtil.uuid(), Long.toString(System.currentTimeMillis() / 1000), appId);

    }

    private void setJsapiTicket() throws Exception {
        if (Global.getAccessTokenExpires() < System.currentTimeMillis())
            setAccessToken();
        String getJsapiTicketUrl = WeiXinConfig.getValue("getJsapiTicketUrl");
        JSONObject jsonObject = CommonUtil.httpsRequest(getJsapiTicketUrl.replace("ACCESS_TOKEN", Global.getAccessToken()), "GET", (String) null);
        if (jsonObject != null) {
            if (jsonObject.getInt("errcode") != 0)
                throw new Exception(jsonObject.getString("errmsg"));
            Global.setJsapiTicket(jsonObject.getString("ticket"));
            Global.setJsapiTicketExpires(jsonObject.getLong("expires_in") + System.currentTimeMillis() - 120);//允许有两分钟误差
        }
    }

    /**
     * access_token 和 过期的时间（时间戳：当前时间+过期时间{7200}）
     */
    public void setAccessToken() throws Exception {
        String appId = WeiXinConfig.getValue("appid");//获得appID
        String getAccessTokenUrl = WeiXinConfig.getValue("getAccessTokenUrl");//去微信第三方平台请求地址，返回access_token等信息
        String secret = WeiXinConfig.getValue("secret");//获得appID
        JSONObject jsonObject = CommonUtil.httpsRequest(getAccessTokenUrl.replace("APPID", appId).replace("SECRET", secret), "GET", (String) null);
        if (jsonObject != null) {//这段代码的意思：如果errorcode不存在，说明正确返回数据，那么catch的时候把正确的值赋进去；如果没有报异常，则说明access_token的值没有获取
            int code =0;
            try {
                code = jsonObject.getInt("errcode");
            }catch (Exception e){
            }
            if(code!=0)
                throw new Exception(jsonObject.getString("errmsg"));
            Global.setAccessToken(jsonObject.getString("access_token"));
            Global.setAccessTokenExpires(jsonObject.getLong("expires_in") + System.currentTimeMillis() - 120);//允许有两分钟误差
        }
    }
}
