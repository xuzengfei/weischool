package com.bugframework.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.common.utility.ResultCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.ModuleDao;
import com.bugframework.auth.pojo.Module;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:服务接口实现层</p>
 * <p>Description: 服务接口实现层</p>
 * @author 许增飞
 * @date 2016-8-14 下午12:31:09
 */

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
 
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public List<Module> find(int delFlag, int iseabled) {
		return moduleDao
				.find("from Module a where a.delFlag =? and a.isenable =? order by orderby asc,createTime desc",
						delFlag, iseabled);
	}

	@Override
	public List<Module> find() {
		return this.moduleDao
				.find("from Module a where a.delFlag =? order by a.orderby asc,a.createTime desc",
						0);
	}

	@Override
	public List<Module> find(String name, String pid) {
		return this.moduleDao.find(
				" from Module r where r.name=? and  r.pid =?", name, pid);
	}

	@Override
	public void datagrid(Module module, DataGrid<Module> datagrid,
			HttpServletRequest request) {
		this.moduleDao.datagrid(module, datagrid, request);
	}

	@Override
	public Module get(String id) {
		if (id == null)
			return new Module();
		return this.moduleDao.get(id);
	}

	@Override
	public ResultCode update(Module s) {
		if(s.getId()==null)
			return ResultCode.INVALID;
		this.moduleDao.update(s);
		return  ResultCode.SUCCESS;

	}

	@Override
	public ResultCode add(Module module) {
		this.moduleDao.add(module);
		return  ResultCode.SUCCESS;
	}

	@Override
	public void deleteAlllogic(String id) {
		if (id != null)
			this.moduleDao.deleteAlllogic(id);

	}

	@Override
	public void deletelogic(String id) {
		if (id != null)
			this.moduleDao.deleteAlllogic(id);

	}

	@Override
	public List<Module> get(String[] ids) {
	    if(ids.length==0)
	    	return new ArrayList<Module>();
	    this.moduleDao.getBatch(ids);
		return null;
	}

}
