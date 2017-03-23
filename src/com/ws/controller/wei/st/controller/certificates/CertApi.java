package com.ws.controller.wei.st.controller.certificates;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.attach.Attach;
import com.ws.pojo.certificates.Certificates;
import com.ws.pojo.student.Student;
import com.ws.service.attach.AttachService;
import com.ws.service.certificates.CertService;
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
 * 证书API
 * Created by Administrator on 2017/3/8.
 */
@Controller
@RequestMapping("/wei/st/cert")
public class CertApi {
    @Autowired
    private CertService certService;
    @Autowired
    private AttachService attachService;

    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listClassRegister(HttpServletRequest request, Certificates certificates, DataGrid<Certificates> datagrid) {
        certificates.setIsenable(1);
        certificates.setStudent(new Student(WeiStLoginUtils.getStudentSession().getStId()));
        List<String[]> orders = new ArrayList<String[]>();
        orders.add(new String[]{"cerTime", "desc"});
        datagrid.setOrder(orders);
        certService.datagrid(certificates, datagrid, request);
        for (Certificates cert : datagrid.getDatas()) {
            List<String> list = this.attachService.listPath(cert.getId());
            if (list != null && !list.isEmpty())
                cert.setPic(list.get(0));
        }
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/get/msg/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getNotice(@PathVariable("id") String id) {

        Certificates certificates = this.certService.get(id);
        List<Attach> attaches = attachService.list(new Attach(certificates.getId(), 0));

        if (attaches != null && !attaches.isEmpty()) {
            //   String[] pics = new String()[attaches.size()];
            String[] pics = new String[attaches.size()];
            for (int i = 0; i < attaches.size(); i++) {
                pics[i] = attaches.get(i).getPath();
            }
            certificates.setPics(pics);
        }
        return new AjaxJson(null, true, certificates);
    }

    @RequestMapping(value = "/to/msg/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        return new ModelAndView("/wei/certificateDetail", "id", id);
    }
}
