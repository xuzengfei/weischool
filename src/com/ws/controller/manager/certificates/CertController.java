package com.ws.controller.manager.certificates;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.certificates.Certificates;
import com.ws.pojo.student.Student;
import com.ws.service.certificates.CertService;
import com.ws.service.student.StudentService;
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
@RequestMapping("/web/manager/cert")
public class CertController {

    @Autowired
    private CertService service;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(String stId) {
        return new ModelAndView("/certificates/certlist").addObject("stId", stId);
    }

    /*
     @RequestMapping(value = "/{stId}", method = RequestMethod.GET)
     public ModelAndView list1(@PathVariable String stId) {
         return new ModelAndView("/certificates/certlist").addObject("stId",stId);
     }
 */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Certificates cert,
                             DataGrid<Certificates> datagrid) {
        service.datagrid(cert, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public ModelAndView toadd() {
       String stId = ResourceUtil.getRequest().getParameter("stId");
        if(stId!=null&&!"".equals(stId)){
            Student student = this.studentService.get(stId);
            ResourceUtil.getRequest().setAttribute("stNo",student.getNo());
        }
        return new ModelAndView("/certificates/certadd");
    }

    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toedit(@PathVariable String id) {
        return new ModelAndView("/certificates/certadd").addObject("obj", this.service.get(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Certificates cert) {
        ResultCode code = this.service.add(cert);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case EXCEPTION:
                j = new AjaxJson("数据异常", false, ResultCode.EXCEPTION.getValue());
                break;
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            case NOEXIST:
                j = new AjaxJson("学生账号不存在", false, ResultCode.NOEXIST.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Certificates cert) {
        ResultCode code = this.service.edit(cert);
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
        Certificates cert = new Certificates();
        cert.setId(id);
        cert.setIsenable(status);
        cert.setDelFlag(null);// 因为delFlag有个默认值
        ResultCode code = this.service.edit(cert);
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
        Certificates cert = new Certificates();
        cert.setId(id);
        cert.setDelFlag(1);
        cert.setDelTime(System.currentTimeMillis());
        ResultCode code = this.service.del(cert);
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
}
