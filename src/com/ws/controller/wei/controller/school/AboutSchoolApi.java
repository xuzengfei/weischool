package com.ws.controller.wei.controller.school;

import com.bugframework.common.pojo.AjaxJson;
import com.ws.service.school.AboutSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/3/14.
 */
@Controller
@RequestMapping("/wei/st/about/sc")
public class AboutSchoolApi {
    @Autowired
    private AboutSchoolService aboutSchoolService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson get() {
        return new AjaxJson(null, true, this.aboutSchoolService.get());
    }
}
