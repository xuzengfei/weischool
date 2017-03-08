package com.ws.service.grade;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.pojo.PageModel;
import com.ws.pojo.grade.ClassRegister;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public interface ClassRegisterService{

    /**
     * 分页列表
     * @param classRegister
     * @param datagrid
     * @param request
     */
    void datagrid(ClassRegister classRegister, DataGrid<ClassRegister> datagrid, HttpServletRequest request);

    /**
     * 获得正常上课的数量
     * @param gradeId 班级ID
     * @param stId 学生ID
     */
    int getNormalNum(String gradeId, String stId);
}
