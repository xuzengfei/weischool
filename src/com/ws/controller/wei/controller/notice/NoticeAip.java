package com.ws.controller.wei.controller.notice;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.attach.Attach;
import com.ws.pojo.notice.Notice;
import com.ws.service.attach.AttachService;
import com.ws.service.notice.NoticeService;
import org.aspectj.weaver.ast.Not;
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
 * Created by admin on 2017/2/20.
 */
@Controller
@RequestMapping("/wei/st/notice")
public class NoticeAip {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private AttachService attachService;

    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listNotice(HttpServletRequest request, Notice notice, DataGrid<Notice> datagrid) {
        notice.setIsenable(1);
        noticeService.datagrid(notice, datagrid, request);
        if(datagrid.getPageNo()==1){
            List<Notice> list = datagrid.getDatas();
            if(list!=null&&!list.isEmpty()) {
                short i=0;
                for (Notice n:list){
                    if (i==0){
                        List<Attach> attaches = attachService.list(new Attach(n.getId(), 0));
                        if(attaches!=null&&!attaches.isEmpty())
                            n.setPic(attaches.get(0).getPath());
                    }
                  n.setContent(null);//减少网络传输
                }

            }
        }
        return HqlGenerateUtil.datagrid(datagrid);
    }

    @RequestMapping(value = "/get/msg/{id}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getNotice(@PathVariable("id")String id){
        Notice notice = this.noticeService.get(id);
        List<Attach> attaches = attachService.list(new Attach(notice.getId(), 0));
        if(attaches!=null&&!attaches.isEmpty())
            notice.setPic(attaches.get(0).getPath());
        return new AjaxJson(null,true,notice);
    }
    @RequestMapping(value = "/to/msg/view/{id}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id){
        return new ModelAndView("/wei/informDetail","id",id);
    }


}
