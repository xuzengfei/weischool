package com.ws.controller.wei.controller.campus;

import com.bugframework.common.pojo.AjaxJson;
import com.ws.pojo.campus.Campus;
import com.ws.service.campus.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 校区API
 * Created by admin on 2017/2/17.
 */
@Controller
@RequestMapping("/wei/campus")
public class CampusApi {
    @Autowired
    private CampusService campusService;

    /**
     * 获得列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson list() {
        Campus campus = new Campus();
        campus.setDelFlag(0);
        campus.setIsenable(1);
        return new AjaxJson(null, true, this.campusService.find(campus));
    }
}
