package com.ws.service.grade.dao;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.grade.GradeTime;

@Repository("gradeTimeDao")
public class GradeTimeDaoImpl extends BaseDaoImpl<GradeTime> implements GradeTimeDao{

}
