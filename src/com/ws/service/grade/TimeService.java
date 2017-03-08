package com.ws.service.grade;

import java.util.List;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.TimeInfo;

public interface TimeService {
	 /**
     * 添加操作
     *
     * @param t 时间段对象
     * @return ResultCode
     */
	ResultCode add(TimeInfo t);
	/**
	 * 通过用户ID获得时间列表
	 * @param userId
	 * @return  
	 */
	List<TimeInfo> list(String userId);
	/**
	 * 通过ID删除
	 * @param id 主键
	 * @return
	 */
	ResultCode del(String id);
	
}
