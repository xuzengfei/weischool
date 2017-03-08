package com.ws.controller.manager.notice;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.notice.Notice;
import com.ws.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/17
 */
@Controller
@RequestMapping("web/manager/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 跳转到noticelist公告列表界面
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "notice/noticelist";
    }

    /**
     * 获得公告列表数据
     *
     * @param request  请求信息
     * @param notice   公告信息
     * @param datagrid 分页数据
     * @return 返回AjaxJson封装的List公告集合
     */
    @RequestMapping(value = "/datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, Notice notice, DataGrid<Notice> datagrid) {
        noticeService.datagrid(notice, datagrid, request);
      /*  List<Notice> notices =  datagrid.getDatas();
        for(Notice n : notices){
            System.out.println(n.getTitle());
        }*/
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 进入发布公告界面
     *
     * @return /notice/noticeadd界面
     */
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public ModelAndView toadd() {
        return new ModelAndView("/notice/noticeadd");
    }

    /**
     * 发布公告
     *
     * @param notice 公告信息
     * @return 返回AjaxJson封装的发布公告的操作状态
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(Notice notice) {
        ResultCode code = noticeService.add(notice);
        AjaxJson j;
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
     * 跳转到更新公告界面
     *
     * @param id 公告id
     * @return 返回/notice/noticeadd公告更新界面
     */
    @RequestMapping(value = "/to/edit/{id}", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable String id) {
        return new ModelAndView("/notice/noticeadd").addObject("obj", noticeService.get(id));
    }

    /**
     * 更新公告信息
     *
     * @param notice 公告信息
     * @return 返回AjaxJson封装的更新公告操作状态
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson edit(Notice notice) {
        ResultCode code = noticeService.update(notice);
        AjaxJson j;
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
     * 启用/禁用公告
     *
     * @param id       公告id
     * @param isenable 公告状态
     * @return 返回AjaxJson封装的操作状态
     */
    @RequestMapping(value = "/isenable/{id}/{isenable}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxJson isenable(@PathVariable String id, @PathVariable Integer isenable) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setIsenable(isenable);
        ResultCode code = noticeService.update(notice);
        AjaxJson j;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setDelFlag(1);
        notice.setDelTime(System.currentTimeMillis());
        AjaxJson j;
        ResultCode code = noticeService.update(notice);
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
}
