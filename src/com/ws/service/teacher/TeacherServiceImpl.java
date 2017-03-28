package com.ws.service.teacher;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.teacher.Teacher;
import com.ws.service.teacher.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师管理服务层接口实现类
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/18
 */
@Service(value = "teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /**
     * 分页查询教师
     *
     * @param teacher  教师信息信息
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    @Override
    public void datagrid(Teacher teacher, DataGrid<Teacher> datagrid, HttpServletRequest request) {
        List<String[]> orders = new ArrayList<>();
        orders.add(new String[]{"createTime", "desc"});
        datagrid.setOrder(orders);
        teacherDao.datagrid(teacher, datagrid, request);
    }

    /**
     * 添加教师
     *
     * @param teacher 教师信息
     * @return 返回ResultCode封装的添加教师操作状态
     */
    @Override
    public ResultCode add(Teacher teacher) {
        if (teacher == null) {
            return ResultCode.INVALID;
        }
        this.teacherDao.add(teacher);
        return ResultCode.SUCCESS;
    }

    /**
     * 更新教师信息
     *
     * @param teacher 教师信息
     * @return 返回ResultCode封装的更新操作状态
     */
    @Override
    public ResultCode update(Teacher teacher) {
        if (teacher.getId() == null || "".equals(teacher.getId())) {
            return ResultCode.INVALID;
        }
        this.teacherDao.update(teacher);
        return ResultCode.SUCCESS;
    }

    /**
     * 查询教师信息
     *
     * @param id 教师id
     * @return 返回操作后的Teacher对象实例
     */
    @Override
    public Teacher get(String id) {
        return this.teacherDao.get(id);
    }

    @Override
    public Teacher getByNo(String no) {
        if (no == null)
            return null;
       return this.teacherDao.findUniqueResult("from Teacher t where t.emNo  =? and t.isenable =? and t.delFlag =?", no, 1, 0);
    }
}
