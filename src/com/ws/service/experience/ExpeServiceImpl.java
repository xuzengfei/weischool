package com.ws.service.experience;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.experience.Experience;
import com.ws.service.attach.AttachService;
import com.ws.service.experience.dao.ExpeDao;
import com.ws.service.student.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 成长经历服务接口实现层
 */
@Service(value = "expeService")
public class ExpeServiceImpl implements ExpeService {
 
	@Autowired
	private ExpeDao expeDao;
	@Autowired
	private StudentService studentService;
	@Autowired
	private AttachService attachService;

	@Override
	public ResultCode add(Experience expe) {

		if (expe.getStudent() == null || expe.getStudent().getNo() == null
				|| "".equals(expe.getStudent().getNo()))
			return ResultCode.INVALID;
		expe.setStudent(studentService.findByNo(expe.getStudent().getNo()));
		if (expe.getStudent() == null)
			return ResultCode.NOEXIST;
		this.expeDao.add(expe);
		this.attachService.edit(expe.getPics(), expe.getId());
		return ResultCode.SUCCESS;
	}

	@Override
	public ResultCode edit(Experience expe) {

		if (expe.getId() == null)
			return ResultCode.INVALID;
		this.expeDao.update(expe);
		return ResultCode.SUCCESS;
	}

	@Override
	public Experience get(String id) {
		if (id == null)
			return null;
		return this.expeDao.get(id);
	}

	@Override
	public void datagrid(Experience expe, DataGrid<Experience> datagrid,
			HttpServletRequest request) {
		this.expeDao.datagrid(expe, datagrid, request);

	}
	@Override
	public ResultCode del(Experience expe) {
		if (expe.getId() == null)
			return ResultCode.INVALID;
		this.expeDao.update(expe);
		this.attachService.edit(expe.getId(), 1);
		return ResultCode.SUCCESS;
	}

}
