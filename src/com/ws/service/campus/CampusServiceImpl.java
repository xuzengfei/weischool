package com.ws.service.campus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.campus.Campus;
import com.ws.service.campus.dao.CampusDao;

@Service(value = "campusService")
public class CampusServiceImpl implements CampusService {
 
	@Autowired
	private CampusDao campusDao;

	@Override
	public void datagrid(Campus campus, DataGrid<Campus> datagrid,
			HttpServletRequest request) {
		this.campusDao.datagrid(campus, datagrid, request);
	}

	@Override
	public ResultCode add(Campus campus) {
		 
			if (campus.getName() == null)
				return ResultCode.INVALID;
			if (!this.findByName(campus.getName()).isEmpty())
				return ResultCode.EXIST;
			this.campusDao.add(campus);
		return ResultCode.SUCCESS;
	}

	@Override
	public ResultCode update(Campus campus, boolean isNameExistValid) {
		 
			if (campus.getId() == null)
				return ResultCode.INVALID;
			if(isNameExistValid&&campus.getName()!=null){
				Campus r =  this.get(campus.getId()) ;
				 if(!campus.getName().equals(r.getName())&&!this.findByName(campus.getName()).isEmpty())
				 return ResultCode.EXIST;
			}
			this.campusDao.update(campus);
			return ResultCode.SUCCESS;
			
	}

	@Override
	public Campus get(String id) {
		 if(id==null)
			 return null;
		return  this.campusDao.get(id);
	}
 
	public List<Campus> findByName(String name) {
		return this.campusDao.find(" from Campus r where r.name=? and  r.delFlag=?", name,0);
	}

	@Override
	public List<Campus> find(Campus campus) {
		return this.campusDao.find(" from Campus r where r.isenable=? and r.delFlag=? order by r.orderby asc", campus.getIsenable(),campus.getDelFlag());
	}
}
