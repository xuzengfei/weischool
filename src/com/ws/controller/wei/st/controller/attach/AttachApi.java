package com.ws.controller.wei.st.controller.attach;

import com.bugframework.common.pojo.AjaxJson;
import com.ws.controller.wei.st.common.WeiStLoginUtils;
import com.ws.pojo.attach.Attach;
import com.ws.pojo.student.StudentOpenId;
import com.ws.service.attach.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/1/10.
 */
@Controller
@RequestMapping("/wei/st/attach")
public class AttachApi {
    @Autowired
    private AttachService attachService;

    //获取
    @RequestMapping(value = "/list/{moduleId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson list(@PathVariable String moduleId) {
        Attach attach = new Attach();
        attach.setModuleId(moduleId);
        List<Attach> list = this.attachService.list(attach);
        AjaxJson j = new AjaxJson();
        j.setObj(list);
        j.setSuccess(true);
        return j;
    }
    @RequestMapping(value = "/list/st/pic",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getPic(){
        StudentOpenId s = WeiStLoginUtils.getStudentSession();
        Attach attach = new Attach();
        attach.setModuleId( WeiStLoginUtils.getStudentSession().getStId());
        List<Attach> list = this.attachService.list(attach);
        AjaxJson j = new AjaxJson();
        j.setObj(list);
        j.setSuccess(true);
        return j;
    }
}
