package com.ws.controller.wei.controller.campuslife;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.controller.wei.common.WeiLoginUtils;
import com.ws.pojo.attach.Attach;
import com.ws.pojo.campuslife.CpLife;
import com.ws.pojo.notice.Notice;
import com.ws.service.attach.AttachService;
import com.ws.service.campuslife.CpLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 校园生活
 * Created by admin on 2017/3/10.
 */
@Controller
@RequestMapping("/wei/st/cplife")
public class CpLifeApi {
    @Autowired
    private CpLifeService cpLifeService;
    @Autowired
    private AttachService attachService;

    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, CpLife cpLife, DataGrid<CpLife> datagrid) {
        cpLife.setIsenable(1);
        cpLife.setCpId(WeiLoginUtils.getStudentSession().getCpId());
        cpLifeService.datagrid(cpLife, datagrid, request);
        List<CpLife> list = datagrid.getDatas();
        if(list!=null&&!list.isEmpty()) {
            for(CpLife c:list){
                List<Attach> attaches = attachService.list(new Attach(c.getId(), 0));
                if(attaches!=null&&!attaches.isEmpty())
                    c.setPic(attaches.get(0).getPath());
            }
        }
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/to/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        return new ModelAndView("/wei/shoolLifeDetail", "id", id);
    }

    @RequestMapping(value = "/get/msg/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson get(@PathVariable String id) {
        CpLife cpLife = this.cpLifeService.get(id);
        List<Attach> attaches = attachService.list(new Attach(cpLife.getId(), 0));
        if(attaches!=null&&!attaches.isEmpty())
            cpLife.setPic(attaches.get(0).getPath());
        return new AjaxJson(null, true, cpLife);
    }

}
