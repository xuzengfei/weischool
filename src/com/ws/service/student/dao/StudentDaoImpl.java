package com.ws.service.student.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.student.Student;
import org.springframework.stereotype.Repository;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
@Repository(value = "studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
}
