package com.bugframework.auth.pojo;

import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.pojo.IdEntity;

import javax.persistence.*;
/**
 * 
 * <p>Title:用户登录表</p>
 * <p>Description: 用户登录表</p>
 * @author 许增飞
 * @date 2016-10-9 下午11:12:04
 */
@Entity
@Table(name = "auth_user_ac")
public class UserAccount extends IdEntity{
	/**
	 * 帐号
	 */
	private String account; 
	/**
	 * 密码
	 */
	private String password; 
	/**
	 * 角色表
	 */
	private Role sysRole;
	/**
	 * 角色ID（查询使用的，不是auth_user_ac里面的属性）
	 */
	private String roleId;
	/**
	 * 角色名称（查询使用的，不是auth_user_ac里面的属性）
	 */
	//private String roleName;
	/**
	 * 用户信息ID 
	 */
	private User user;
	
	
	
	/* 
	@Override
	public String toString() {
		return "{\"account\":\"" + account + "\",\"password\":\"" + password
				+ "\",\"sysRole\":\"" + sysRole + "\",\"roleName\":\""
				+ roleName + "\"}";
	}*/

	@Column(name = "account", length = 50, nullable = true, unique = true)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 200)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*@ForeKey(category="sysRole",column="name")
 	@Transient
	public String getRoleName() {
		if(this.sysRole!=null&&this.sysRole.getName()!=null)
			return this.sysRole.getName();
		return null;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	} */
	@ForeKey(category="sysRole",column="id")
	@Transient
	public String getRoleId() {
		if(this.sysRole!=null&&this.sysRole.getId()!=null)
			return this.sysRole.getId();
		return null;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@OneToOne
	@JoinColumn(name = "role_id")
	public Role getSysRole() {
		return sysRole;
	}

	public void setSysRole(Role sysRole) {
		 
		this.sysRole = sysRole;
	}

	/**  
	 * 获取用户信息ID  
	 * @return user 用户信息ID  
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	/**  
	 * 设置用户信息ID  
	 * @param user 用户信息ID  
	 */
	public void setUser(User user) {
		this.user = user;
	}
    /**
	 * 添加数据是否合法
	 * @return
     */
	@Transient
	public boolean isAddValid(){
		if(account==null||"".equals(account)||password==null||"".equals(password)||sysRole.getId()==null||"".equals(sysRole.getId())){
			return false;
		}
		return true;
	}
	
}
