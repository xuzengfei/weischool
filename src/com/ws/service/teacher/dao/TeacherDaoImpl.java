package com.ws.service.teacher.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.teacher.Teacher;
import org.springframework.stereotype.Repository;

/**
 * 教师管理数据层接口实现类
 *
 * @author LuoZexiang
 * @version v1.0
 * @since 2017/1/18
 */
@Repository(value = "teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao {
}
