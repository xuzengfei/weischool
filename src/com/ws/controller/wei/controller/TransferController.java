package com.ws.controller.wei.controller;

import com.ws.controller.wei.common.PageEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 界面中转
 * Created by admin on 2017/2/16.
 */
@Controller
@RequestMapping("/wei/st/to")
public class TransferController {
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public ModelAndView transfer(@PathVariable String page){
         return new ModelAndView("/wei/"+PageEnum.findPage(page));
    }
}
