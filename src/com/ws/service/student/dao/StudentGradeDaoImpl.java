package com.ws.service.student.dao;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.student.StudentGrade;
@Repository(value="studentGradeDao")
public class StudentGradeDaoImpl extends BaseDaoImpl<StudentGrade> implements StudentGradeDao{
	
}
