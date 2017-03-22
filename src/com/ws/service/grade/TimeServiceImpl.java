package com.ws.service.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.TimeInfo;
import com.ws.service.grade.dao.TimeDao;

@Service("timeService")
public class TimeServiceImpl implements TimeService{
	@Autowired
	private TimeDao dao;
	@Override
	public ResultCode add(TimeInfo t) {
		if(t.getTitle()==null)
			return ResultCode.INVALID;
		this.dao.add(t);
		return ResultCode.SUCCESS;
	}
	@Override
	public List<TimeInfo> list(String userId) {
		if(userId==null)
			return null;
		return this.dao.find("from TimeInfo where createBy = ? order by createTime asc", userId);
	}
	@Override
	public ResultCode del(String id) {
		if(id==null)
			return ResultCode.INVALID;
		 this.dao.delete(id);
		 return ResultCode.SUCCESS;
	}

}
