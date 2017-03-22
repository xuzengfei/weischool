package com.ws.service.experience.dao;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.experience.Experience;

@Repository(value="expeDao")
public class ExpeDaoImpl extends BaseDaoImpl<Experience> implements ExpeDao{

}
