package com.bugframework.auth.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;
/**
 * 
 * <p>Title:系统角色模型 </p>
 * <p>Description: 对角色进行管理</p>
 * @author 许增飞
 * @date 2016-10-9 下午11:15:41
 */
@Entity
@Table(name="auth_role")
public class Role extends IdEntity {
	/**
	 * 权限名称
	 */
	private String name; 
	/**
	 * 描述
	 */
	private String remark; 
	/**
	 * 是否为超级管理员：1--是 --否(默认)
	 */
	private Short isAdmin=0; 
	
	
	public Role(){
		
	}
	public Role(String name, String remark, Short isAdmin) {
		this.name = name;
		this.remark = remark;
		this.isAdmin = isAdmin;
	}
	public Role(Short isAdmin,Integer isenable,Integer delFlag) {
		this.isAdmin = isAdmin;
		super.setIsenable(isenable);
		super.setDelFlag(delFlag);
	}
	
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\",\"remark\":\"" + remark
				+ "\",\"isAdmin\":\"" + isAdmin + "\"}";
	}
	/**  
	 * 获取权限名称  
	 * @return name 权限名称  
	 */
	@Column(name="name",length=20)
	public String getName() {
		return name;
	}
	/**  
	 * 设置权限名称  
	 * @param name 权限名称  
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**  
	 * 获取描述  
	 * @return remark 描述  
	 */
	@Column(name="remark",length=100)
	public String getRemark() {
		return remark;
	}
	/**  
	 * 设置描述  
	 * @param remark 描述  
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**  
	 * 获取是否为超级管理员：1--是--否(默认)  
	 * @return isAdmin 是否为超级管理员：1--是--否(默认)  
	 */
	@Column(name="is_admin",length=1)
	public Short getIsAdmin() {
		return isAdmin;
	}
	/**  
	 * 设置是否为超级管理员：1--是--否(默认)  
	 * @param isAdmin 是否为超级管理员：1--是--否(默认)  
	 */
	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
