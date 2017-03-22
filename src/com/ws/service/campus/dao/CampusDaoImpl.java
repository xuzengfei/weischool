package com.ws.service.campus.dao;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.campus.Campus;
@Repository(value = "campusDao")
public class CampusDaoImpl extends BaseDaoImpl<Campus> implements CampusDao{

}
