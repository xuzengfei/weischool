package com.ws.service.student;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.student.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
public interface StudentService {

    /**
     * 通过ID获取对象
     *
     * @param id 主键
     * @return
     */
    Student get(String id);

    /**
     * 更新
     *
     * @param student 传入更新对象
     * @return
     */
    ResultCode edit(Student student, boolean isExistValid);

    /**
     * 添加
     *
     * @param student 传入添加对象
     * @return
     */
    ResultCode add(Student student);

    /**
     * 分页查询
     *
     * @param student  班级对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Student student, DataGrid<Student> datagrid, HttpServletRequest request);

    /**
     * 通过账号查找用户
     * @param no 账号
     * @return
     */
    Student findByNo(String no);

    List<Student> find(Student student);
}
