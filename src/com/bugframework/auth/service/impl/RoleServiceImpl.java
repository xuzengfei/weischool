package com.bugframework.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.Role;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
/**
 * 
 * <p>Title:RoleServiceImpl </p>
 * <p>Description:角色服务实现层 </p>
 * @author 许增飞
 * @date 2016-8-14 上午9:17:20
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
 
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getRoleList() {
		return this.roleDao.getRoleList(new Role((short)0,1,0));
			
	}

	@Override
	public Role get(String id) {
		if (id == null)
			return new Role();
		return this.roleDao.get(id);
	}

	@Override
	public void datagrid(Role role, DataGrid<Role> datagrid,
			HttpServletRequest request) {
		 this.roleDao.datagrid(role, datagrid, request);
		
	}

	@Override
	public ResultCode update(Role role,boolean isNameExistValid) {
	 
		if (role.getId() == null)
			return ResultCode.INVALID;
		if(isNameExistValid&&role.getName()!=null){
			 Role r =  this.get(role.getId()) ;
			 if(!role.getName().equals(r.getName())&&!this.findByName(role.getName()).isEmpty())
			 return ResultCode.EXIST;
		}
		this.roleDao.update(role);
		return ResultCode.SUCCESS;
		
		
	}

	@Override
	public void deleteAlllogic(String id) {
		 this.roleDao.deleteAlllogic(id);
		
	}

	@Override
	public void delete(String id) {
		this.roleDao.delete(id);
		
	}

	@Override
	public ResultCode add(Role role) {
			 if(role.getName()==null)
				 return ResultCode.INVALID;
			 if(!this.findByName(role.getName()).isEmpty())
				 return ResultCode.EXIST;
			this.roleDao.add(role);
		return ResultCode.SUCCESS;
		
	}

	@Override
	public List<Role> findByName(String name) {
		return this.roleDao.find(" from Role r where r.name=?", name);
	}
 

}
