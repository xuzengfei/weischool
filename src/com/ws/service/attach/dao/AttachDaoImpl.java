package com.ws.service.attach.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.attach.Attach;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/1/13.
 */
@Repository(value = "attachDao")
public class AttachDaoImpl extends BaseDaoImpl<Attach> implements  AttachDao{

}
