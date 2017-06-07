package com.ws.controller.wei.st.controller.pay;

import com.bugframework.common.pojo.AjaxJson;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.coupon.Coupon;
import com.ws.pojo.grade.Grade;
import com.ws.pojo.grade.GradeCostTpl;
import com.ws.service.coupon.CoupService;
import com.ws.service.grade.GradeCostTplService;
import com.ws.service.grade.GradeService;
import com.ws.service.weixin.WeixinLoginService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/jssdk/congfig", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getJsSdkConfig() {
        Map<String,String> result = null;
        try {
            result = weixinLoginService.getWeiConfig("https://www.linglonged.com");
            return new AjaxJson(null, true, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return  new AjaxJson("验证失败,请联系管理员。", false, result);
    }

}
