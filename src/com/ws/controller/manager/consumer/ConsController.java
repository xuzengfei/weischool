package com.ws.controller.manager.consumer;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.consumer.Consumer;
import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.consumer.ConsService;
import com.ws.service.coupon.CoupService;
import com.ws.service.student.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
@Controller
@RequestMapping("/web/manager/cons")
public class ConsController {
    @Autowired
    private ConsService service;
    @Autowired
    private CoupService coupService;
    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list( String stId, String cpId, String graId,String name,String idNo) {
        return new ModelAndView("/consumer/conslist").addObject("stId", stId).addObject("cpId", cpId).addObject("graId", graId).addObject("name", name).addObject("idNo", idNo);
    }

    @RequestMapping(value = "datagrid/{stId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Consumer cons,
                             DataGrid<Consumer> datagrid, @PathVariable String stId) {
        cons.setStudent(new StudentGrade().id(stId));
        service.datagrid(cons, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/to/add/{stId}", method = RequestMethod.GET)
    public ModelAndView toadd(@PathVariable String stId) {
        StudentGrade studentGrade =studentGradeService.get(stId);
        return new ModelAndView("/consumer/consadd").addObject("stId", stId);
    }

    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id) {
        return null;
        //   return new ModelAndView("/experience/expeadd").addObject("obj", this.service.get(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Consumer cons,String coupId) {
        ResultCode code = this.service.add(cons,coupId);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Consumer consumer) {
        ResultCode code = this.service.edit(consumer);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    /**
     * 启用/禁用
     *
     * @param id     传入主键ID
     * @param status 启用或者禁用标记
     * @return 返回 AjaxJson，具体的值参考 AjaxJson
     */
    // TODO
    @RequestMapping(value = "/isenable/{id}/{status}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson isenable(@PathVariable String id, @PathVariable Integer status) {
        AjaxJson j = new AjaxJson();
        Consumer cons = new Consumer();
        cons.setId(id);
        cons.setIsenable(status);
        cons.setDelFlag(null);// 因为delFlag有个默认值
        ResultCode code = this.service.edit(cons);
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", false, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    /**
     * 删除，逻辑删除
     *
     * @param id 传入ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        AjaxJson j = new AjaxJson();
        Consumer cons = new Consumer();
        cons.setId(id);
        cons.setDelFlag(1);
        cons.setDelTime(System.currentTimeMillis());
        ResultCode code = this.service.edit(cons);
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", false, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }
    public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis()+1000*60*60*12);
	}
}
