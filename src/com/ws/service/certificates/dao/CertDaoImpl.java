package com.ws.service.certificates.dao;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.certificates.Certificates;

@Repository(value="certDao")
public class CertDaoImpl extends BaseDaoImpl<Certificates> implements CertDao{

}
