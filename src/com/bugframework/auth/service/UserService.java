package com.bugframework.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.auth.pojo.User;
import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:用户服务接口层 </p>
 * <p>Description: 用户服务接口层</p>
 * @author 许增飞
 * @date 上午8:43:28
 */
public interface UserService {
	/**
	 * 查找用户，通过帐号和密码
	 * 
	 * @param user
	 * @return
	 */
	UserAccount findUser(UserAccount user);

	/**
	 * 查询用户账号
	 * @param name UserAccount属性名称
	 * @param value UserAccount属性值
	 * @return
	 */
	List<UserAccount> findUser(String name,String value);

	/**
	 * 获取可用的用户
	 * 
	 * @return
	 */
	List<User> enableUser();

	/**
	 * 更新用户
	 * 
	 * @param sysuser
	 */
	void update(User sysuser);

	/**
	 * 获得分页列表
	 * 
	 * @param user
	 *            用户
	 * @param datagrid
	 *            分页模型
	 * @param request
	 *            HttpServletRequest
	 */
	void datagrid(User user, DataGrid<User> datagrid,
                  HttpServletRequest request);

	/**
	 * 通过主键获取用户
	 * 
	 * @param id
	 * @return
	 */
	User get(String id);

	/**
	 * 添加用户
	 * 
	 * @param sysUser
	 */
	void add(User sysUser);

	/**
	 * 批量逻辑删除
	 * 
	 * @param id
	 *            传入用户ID，多个ID 逗号隔开
	 */
	void deleteAlllogic(String id);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 */
	void deletelogic(String id);

	/**
	 * 获取用户
	 * 
	 * @param name
	 *            用户表属性名称
	 * @param value
	 *            用户表属性值
	 * @return List<AuthUser>
	 */
	List<User> getUser(String name, String value);


}
