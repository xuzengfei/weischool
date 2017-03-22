package com.ws.service.grade.dao;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.grade.TimeInfo;
@Repository("timeDao")
public class TimeDaoImpl extends BaseDaoImpl<TimeInfo> implements TimeDao{

}
