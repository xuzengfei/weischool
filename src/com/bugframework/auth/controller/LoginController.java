package com.bugframework.auth.controller;

import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.PermissionService;
import com.bugframework.auth.service.UserService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.RSAUtils;
import com.bugframework.common.utility.ResourceUtil;
import com.google.code.kaptcha.Constants;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ModuleService moduleService;


    /**
     * 把公钥和随机数传给login.jsp
     *
     * @param request
     */
    @RequestMapping(value = "/checkKey", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkKey(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();// 拿到公钥,公钥有两个参数modulus和exponent
        String modulus = new String(Hex.encodeHex(publicKey.getModulus()
                .toByteArray()));
        String exponent = new String(Hex.encodeHex(publicKey
                .getPublicExponent().toByteArray()));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("modulus", modulus);
        map.put("exponent", exponent);
        map.put("time", new Date().getTime());
        j.setSuccess(true);
        j.setAttributes(map);
        return j;

    }

    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson checkuser(String longkey, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String code = (String) ResourceUtil.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        try {
            if (!DataUtils.isStrEmpty(longkey)) {
                String hruser = RSAUtils.decryptStringByJs(longkey);
                String[] longkeyArray = hruser.split(",");
                /*
                 * if(code.equalsIgnoreCase(longkeyArray[0])){//验证码
				 */
                if (true) {// 验证码
                    long nowTime = new Date().getTime();
                    long prevTime = Long.parseLong(longkeyArray[1]);
                    long time = nowTime - prevTime;
                    // if(time>60000){//时间超过一秒就不给通过
                    UserAccount user = new UserAccount();
                    user.setAccount(java.net.URLDecoder.decode(longkeyArray[2], "UTF-8"));
                    user.setPassword(java.net.URLDecoder.decode(longkeyArray[3], "UTF-8"));
                    UserAccount sysuser = userService.findUser(user);
                    if (sysuser != null) {
                        ResourceUtil.getSession().setMaxInactiveInterval(60*60*1000);
                        ResourceUtil.getSession().setAttribute(ResourceUtil.USER_SESSION, sysuser);
                        j.setMsg("操作成功！");
                        j.setObj(1);
                        j.setSuccess(true);
                    } else {
                        j.setMsg("帐号或密码输入错误！");
                        j.setObj(0);
                    }
                } else {
                    j.setMsg("验证码输入错误！");
                    j.setObj(-2);
                }

            }
        } catch (Exception e) {
            j.setMsg("服务器异常，请联系管理员！");
            e.printStackTrace();
            j.setObj(0);
        }
        ResourceUtil.getSession()
                .removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        return j;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String url = "login";
        UserAccount user = ResourceUtil.getUserSession();
        if (user != null) {
            url = "redirect:/admin";
        }
        return url;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("home");
    }


    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "loginout", method = RequestMethod.GET)
    public ModelAndView loginout(HttpServletRequest request) {
        ResourceUtil.removeSession();
        return new ModelAndView("login");
    }

}
