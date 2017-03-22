package com.ws.service.student.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl1;
import com.ws.pojo.student.StudentOpenId;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/2/15.
 */
@Repository(value = "studentOpenIdDao")
public class StudentOpenIdDaoImpl extends BaseDaoImpl1<StudentOpenId> implements StudentOpenIdDao {
}
