package com.ws.controller.manager.school;

import com.bugframework.common.pojo.AjaxJson;
import com.ws.pojo.school.AboutSchool;
import com.ws.service.school.AboutSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 2017/3/14.
 */
@Controller
@RequestMapping("/web/manager/about/sc")
public class AboutSchoolController {

    @Autowired
    private AboutSchoolService aboutSchoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/school/aboutschool");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson get() {
        return new AjaxJson(null, true, this.aboutSchoolService.get());
    }

    @RequestMapping(value = "/addorup", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addorup(AboutSchool aboutSchool) {
        return new AjaxJson().result(this.aboutSchoolService.addOrUp(aboutSchool));
    }

}
