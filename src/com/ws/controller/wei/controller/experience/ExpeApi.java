package com.ws.controller.wei.controller.experience;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.controller.wei.common.WeiLoginUtils;
import com.ws.pojo.attach.Attach;
import com.ws.pojo.experience.Experience;
import com.ws.pojo.student.Student;
import com.ws.service.attach.AttachService;
import com.ws.service.experience.ExpeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */
@Controller
@RequestMapping("/wei/expe")
public class ExpeApi {
    @Autowired
    private ExpeService service;
    @Autowired
    private AttachService attachService;

    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson list(HttpServletRequest request, Experience experience, DataGrid<Experience> datagrid) {
        experience.setIsenable(1);
        experience.setStudent(new Student(WeiLoginUtils.getStudentSession().getStId()));
        List<String[]> orders = new ArrayList<String[]>();
        orders.add(new String[]{"expeTime", "desc"});
        datagrid.setOrder(orders);

        service.datagrid(experience, datagrid, request);
        for (Experience e:datagrid.getDatas()){
            e.setPicPaths(this.attachService.listPath(e.getId()));
        }
        return HqlGenerateUtil.datagrid(datagrid);
    }
}
