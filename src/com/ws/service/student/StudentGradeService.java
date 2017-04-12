package com.ws.service.student;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.student.StudentGrade;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
public interface StudentGradeService {

    /**
     * 通过ID获取对象
     *
     * @param id 主键
     * @return
     */
    StudentGrade get(String id);

    /**
     * 更新
     *
     * @param studentGrade 传入更新对象
     * @return
     */
    ResultCode edit(StudentGrade studentGrade, boolean isExistValid);

    /**
     * 添加
     *
     * @param studentGrade 传入添加对象
     * @return
     */
    ResultCode add(StudentGrade studentGrade);

    /**
     * 分页查询
     *
     * @param studentGrade 班级对象值
     * @param datagrid     分页模型
     * @param request      HttpServletRequest 请求
     */
    void datagrid(StudentGrade studentGrade, DataGrid<StudentGrade> datagrid, HttpServletRequest request);

    /**
     * 查询列表
     *
     * @param sg 查询对象
     * @return
     */
    List<StudentGrade> find(StudentGrade sg);

    /**
     * 按照剩余课时升序排序获得班级学生的列表
     *
     * @param cpId 校区ID
     * @param stId 学生ID
     * @return
     */
    List<StudentGrade> findOrderRestClass(String cpId, String stId);
    /**
     * 通过班级查询列表
     *
     * @param gradeId 查询ID
     * @return
     */
    List<StudentGrade> findByGrade(String gradeId);
}
