package com.ws.service.grade.dao;

import com.bugframework.common.dao.BaseDao;
import com.ws.pojo.grade.Grade;

import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
public interface GradeDao extends BaseDao<Grade>{
    List<Grade> find(Grade grade);
}
