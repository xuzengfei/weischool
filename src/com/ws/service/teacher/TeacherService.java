package com.ws.service.teacher;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.teacher.Teacher;

import javax.servlet.http.HttpServletRequest;

/**
 * 教师管理服务层接口
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/18
 */
public interface TeacherService {

    /**
     * 分页查询教师
     *
     * @param teacher  教师信息信息
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Teacher teacher, DataGrid<Teacher> datagrid, HttpServletRequest request);

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     * @return 返回ResultCode封装的添加教师操作状态
     */
    ResultCode add(Teacher teacher);

    /**
     * 更新教师信息
     *
     * @param teacher 教师信息
     * @return 返回ResultCode封装的更新操作状态
     */
    ResultCode update(Teacher teacher);

    /**
     * 查询教师信息
     *
     * @param id 教师id
     * @return 返回操作后的Teacher对象实例
     */
    Teacher get(String id);
}
