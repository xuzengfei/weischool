package com.ws.controller.manager.student;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.ws.pojo.student.StudentGrade;
import com.ws.pojo.student.StudentOpenId;
import com.ws.pojo.student.StudentOpenIdVo;
import com.ws.service.student.StudentGradeService;
import com.ws.service.student.StudentOpenIdService;
import com.ws.service.student.StudentOpenIdVoService;
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
 * @author 许增飞
 * @description 学生微信绑定
 * @datetime 2017/6/12 14:38.
 */
@Controller
@RequestMapping("/web/manager/studentopenidvo")
public class StudentOpenIdVoController {
    @Autowired
    private StudentOpenIdVoService service;
    @Autowired
    private StudentOpenIdService studentOpenIdService;
    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toList() {
        return "/student/studentopenidvolist";
    }

    /**
     * 获得列表数据
     *
     * @param request         request对象
     * @param studentOpenIdVo 微信绑定对象值
     * @param datagrid        分页模型
     * @return
     */
    @RequestMapping(value = "datagrid", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson datagrid(HttpServletRequest request, StudentOpenIdVo studentOpenIdVo, DataGrid<StudentOpenIdVo> datagrid) {
        service.datagrid(studentOpenIdVo, datagrid, request);
        return HqlGenerateUtil.datagrid(datagrid);
    }

    /**
     * 解除绑定
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        service.del(id);
        return new AjaxJson("解绑成功", true, null);
    }

    /**
     * 进入添加绑定界面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView toadd(@PathVariable String id) {
        return new ModelAndView("/student/studentopenidvoadd", "id", id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson add(@PathVariable String id, String stIds, String stNames) {
        StudentOpenId studentOpenId = this.studentOpenIdService.getById(id);
        String[] stIdsArray = stIds.split(",");
        String[] stNamesArray = stNames.split(",");
        String resMsg = "";
        for (int i = 0; i < stIdsArray.length; i++) {
            List<StudentGrade> grades = studentGradeService.findByStId(stIdsArray[i]);
            int resultCode = this.studentOpenIdService.save(studentOpenId.getOpenId(),stIdsArray[i], grades.get(0).getCampus().getId(), System.currentTimeMillis());
            if (resultCode == -1) {
                resMsg += "【"+ stNamesArray[i] + "已经绑定了微信】 ";
            }
        }
        if ("".equals(resMsg))
            resMsg = "全部绑定成功";
        return new AjaxJson(resMsg, true, null);
    }
}
