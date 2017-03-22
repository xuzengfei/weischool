package com.ws.controller.manager.coupon;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.coupon.Coupon;
import com.ws.pojo.student.Student;
import com.ws.service.coupon.CoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠/代金券
 * Created by admin on 2017/2/9.
 */
@Controller
@RequestMapping("/web/manager/coupon")
public class CouponController {

    @Autowired
    private CoupService coupService;

    @RequestMapping(value = "/{stId}", method = RequestMethod.GET)
    public ModelAndView couponlist(@PathVariable String stId) {
        return new ModelAndView("/coupon/couponlist", "stId", stId);
    }

    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Coupon coupon,
                             DataGrid<Coupon> datagrid) {
        if (coupon.getStudent() != null && "more".equals(coupon.getStudent().getId()))//系统内部封装datagrid的bug,以后再解决
            coupon.getStudent().setId(null);
        coupService.datagrid(coupon, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /***
     * 有两种情况
     * 1 stId 可能为more ,说明是批量添加
     * 2 stId 是正式的sutdent  ID 
     * @param stId
     * @return
     */
    @RequestMapping(value = "/to/add/{stId}", method = RequestMethod.GET)
    public ModelAndView toAdd(@PathVariable String stId) {
        return new ModelAndView("/coupon/couponadd", "stId", stId);
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toEidt(@PathVariable String id) {
        return new ModelAndView("/coupon/couponadd", "obj", this.coupService.get(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Coupon coupon, String stIds) {
        coupon.setIsenable(0);
        if (stIds != null) {
            List<Coupon> coupons = new ArrayList<>();

            String[] stIdArray = stIds.split(",");
            for (String stId : stIdArray) {
                Student student = new Student();
                student.setId(stId);
                Coupon c = (Coupon) coupon.clone();
                c.setStudent(student);
                coupons.add(c);
            }
            ResultCode code = this.coupService.batchAdd(coupons);
            return new AjaxJson().result(code);
        } else {
            ResultCode code = this.coupService.add(coupon);
            return new AjaxJson().result(code);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Coupon coupon) {
        ResultCode code = this.coupService.edit(coupon);
        return new AjaxJson().result(code);
    }
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson edit(@PathVariable String id) {
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setDelFlag(1);
        ResultCode code = this.coupService.edit(coupon);
        return new AjaxJson().result(code);
    }
}
