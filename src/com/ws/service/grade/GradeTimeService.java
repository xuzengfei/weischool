package com.ws.service.grade;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.GradeTime;

import java.util.List;


public interface GradeTimeService {
	 /**
     * 添加操作
     *
     * @param t 班次对象
     * @return ResultCode
     */
    ResultCode add(GradeTime t);

    List<GradeTime> list(String gradId, Long start, Long end);

    List<GradeTime> list(String[] userId, Long start, Long end);

	ResultCode del(String id);


}
