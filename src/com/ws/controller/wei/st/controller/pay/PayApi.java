package com.ws.controller.wei.st.controller.pay;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.IdUtil;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.WXPayUtil;
import com.souvc.weixin.util.WeiXinConfig;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.consumer.Consumer;
import com.ws.pojo.coupon.Coupon;
import com.ws.pojo.grade.Grade;
import com.ws.pojo.grade.GradeCostTpl;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.consumer.ConsService;
import com.ws.service.coupon.CoupService;
import com.ws.service.grade.GradeCostTplService;
import com.ws.service.grade.GradeService;
import com.ws.service.student.StudentGradeService;
import com.ws.service.weixin.WeixinLoginService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/17.
 */
@Controller
@RequestMapping("/wei/st/pay")
public class PayApi {
    @Autowired
    private GradeCostTplService gradeCostTplService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CoupService coupService;
    @Autowired
    private WeixinLoginService weixinLoginService;
    @Autowired
    private ConsService consService;
    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(value = "/grade/{gradeId}/costtpls", method = RequestMethod.GET)
    public ModelAndView toCostTpl(@PathVariable String gradeId) {
        return new ModelAndView("/wei/fee_chooseTime", "gradeId", gradeId);
    }

    /**
     * 第一步
     * 列出课程费用列表
     *
     * @param gradeId
     * @return
     */
    @RequestMapping(value = "/grade/{gradeId}/costtpls/datas", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listCostTpl(@PathVariable String gradeId) {
        return new AjaxJson(null, true, this.gradeCostTplService.find(gradeId));
    }

    /**
     * 第二步 填写订单
     *
     * @param gradeId
     * @param cosId
     * @return
     */
    @RequestMapping(value = "/grade/{gradeId}/costtpl/{cosId}/free", method = RequestMethod.GET)
    public ModelAndView tofree(@PathVariable String gradeId, @PathVariable String cosId) {
        return new ModelAndView("/wei/fee").addObject("gradeId", gradeId).addObject("cosId", cosId);
    }

    //todo 测试
    @RequestMapping(value = "/grade/free", method = RequestMethod.GET)
    public ModelAndView tofree1(String gradeId, String cosId) {
        return new ModelAndView("/wei/fee").addObject("gradeId", gradeId).addObject("cosId", cosId);
    }

    /**
     * 获得订单数据
     *
     * @param gradeId 班级ID
     * @param cosId   费用模板ID
     * @return AjaxJson
     */
    @RequestMapping(value = "/grade/{gradeId}/costtpl/{cosId}/free/data", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson free(@PathVariable String gradeId, @PathVariable String cosId) {
        Grade grade = this.gradeService.get(gradeId);
        GradeCostTpl gradeCostTpl = this.gradeCostTplService.get(cosId);
        List<Coupon> coupons = this.coupService.listByStudent(WeiStLoginUtils.getStudentSession().getStId());
        Map<String, Object> attributes = new HashedMap();
        attributes.put("gradeName", grade.getName());
        attributes.put("classNum", gradeCostTpl.getClassNum());
        attributes.put("amount", gradeCostTpl.getAmount());
        attributes.put("coupons", coupons);
        AjaxJson j = new AjaxJson();
        j.setAttributes(attributes);
        j.setSuccess(true);
        return j;
    }

    /**
     * 第三步：调用微信支付
     *
     * @param cosId  费用模板ID
     * @param coupId 抵用券ID
     * @return
     */
    @RequestMapping(value = "/cosId/{cosId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson beforePay(@PathVariable String cosId, String coupId) {
        int useRule = 0;
        if (!"-1".equals(coupId)) {
            useRule = Integer.parseInt(this.coupService.get(coupId).getUseRule());
        }
        GradeCostTpl gradeCostTpl = this.gradeCostTplService.get(cosId);
        int free = Integer.parseInt(gradeCostTpl.getAmount()) - useRule;
        free = free < 0 ? 0 : free * 100;
        return getPayCfg("" + free, "玲珑舞艺-"+gradeCostTpl.getRemark());
        //return getPayCfg("1", "玲珑舞艺-" + gradeCostTpl.getRemark());
    }

    /**
     * 第四步： 微信支付成功后，记录到缴费清单
     *
     * @param cosId  费用模板ID
     * @param coupId 抵用券ID
     * @return
     */
    @RequestMapping(value = "/cosId/{cosId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson afterPay(@PathVariable String cosId, String coupId, String gradeId) {
        System.out.println("cosId="+cosId+";coupId="+coupId+";gradeId="+gradeId);
        GradeCostTpl gradeCostTpl = this.gradeCostTplService.get(cosId);
        StudentGrade studentGrade = studentGradeService.find(gradeId, WeiStLoginUtils.getStudentSession().getStId());
        System.out.println("studentGrade="+studentGrade);
        Consumer consumer = new Consumer();
        consumer.setProjectType(1);
        consumer.setProject(gradeCostTpl.getRemark());
        consumer.setPayType("微信支付");
        consumer.setPayTime(System.currentTimeMillis());
        consumer.setStudent(studentGrade);
        consumer.setCreateTime(System.currentTimeMillis());
        consumer.setCreateName("微信支付用户");
        consumer.setCreateBy(WeiStLoginUtils.getStudentSession().getStId());
        consumer.setClassNum(gradeCostTpl.getClassNum());
        consumer.setAmount(gradeCostTpl.getAmount());
        consService.add(consumer, "-1".equals(coupId) ? null : coupId);
        return new AjaxJson(null, true, null);
    }

    @RequestMapping(value = "/jssdk/congfig", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getJsSdkConfig(HttpServletRequest request, String url) {
        Map<String, String> result = null;
        try {
            result = weixinLoginService.getWeiConfig(url);
            return new AjaxJson(null, true, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxJson("验证失败,请联系管理员。", true, result);
    }

    @RequestMapping(value = "/to/jssdk/congfig", method = RequestMethod.GET)
    public String toJsSdkConfig() {
        return "/wei/pay";
    }

    @RequestMapping(value = "/jssdk/paycfg", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson getPayCfg(String fee, String body) {
        if ("0".equals(fee)) {
            return new AjaxJson(null, false, 1);
        }
        String resp = null;
        String ip = ResourceUtil.getIpAddr();
        String appId = WeiXinConfig.getValue("appid");//获得appID
        String notify_url = WeiXinConfig.getValue("notify_url");
        String mch_id = WeiXinConfig.getValue("mch_id");
        String pay_uri = WeiXinConfig.getValue("pay_uri");
        // String openId = "ox7PvwfElwYZUfpiS08RtBRPJd60";
        String openId = WeiStLoginUtils.getStudentSession().getOpenId();
        String key = WeiXinConfig.getValue("key");
        Long s = System.currentTimeMillis();
        String nonce_str = IdUtil.uuid();

        HashMap<String, String> data = new HashMap<>();
        data.put("appid", appId);
        data.put("mch_id", mch_id);
        data.put("device_info", "WEB");
        data.put("nonce_str", nonce_str);
        data.put("body", body);
        data.put("out_trade_no", s.toString());
        data.put("total_fee", fee);
        data.put("spbill_create_ip", ip);
        data.put("notify_url", notify_url);
        data.put("openid", openId);
        data.put("trade_type", "JSAPI");

        try {
            String reqBody = WXPayUtil.generateSignedXml(data, key);
            URL httpUrl = new URL(pay_uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setRequestProperty("Host", ip);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(10 * 1000);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(reqBody.getBytes("UTF-8"));

            //获取内容
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            final StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            resp = stringBuffer.toString();
            Map<String, String> datamap = WXPayUtil.xmlToMap(resp);


            if (stringBuffer != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (datamap.get("return_code").equals("SUCCESS")) {
                Map<String, String> result = new HashedMap();
                result.put("appId", appId);
                result.put("timeStamp", s.toString());
                result.put("nonceStr", nonce_str);
                result.put("package", "prepay_id=" + datamap.get("prepay_id"));
                result.put("signType", "MD5");
                result.put("paySign", WXPayUtil.generateSignature(result, key));
                return new AjaxJson(null, true, result);
            } else {
                return new AjaxJson(null, false, datamap.get("return_msg"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxJson("服务器异常", false, -1);
    }

}
