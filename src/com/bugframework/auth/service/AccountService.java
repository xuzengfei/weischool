package com.bugframework.auth.service;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;

/**
 * 
 * <p>Title:用户帐号服务层</p>
 * <p>Description: </p>
 * @author 许增飞
 * @date 2016-10-18 下午11:33:47
 */
public interface AccountService {
	/**
	 * 分页模型
	 * @param account 帐号对象
	 * @param datagrid 模型
	 * @param request 请求对象
	 */
	void datagrid(UserAccount account, DataGrid<UserAccount> datagrid, HttpServletRequest request);
	/**
	 * 添加用户帐号
	 * 1.校验数据是否合法：帐号、密码、角色ID，启用禁用不能为空
	 * 2.判断帐号是否已经存在了
	 * 3.添加用户信息（USER） 后拿到 user的的主键 然后赋值到account中，再保存到数据库中
	 * 
	 * @param account 用户帐号信息
	 * @return 返回ResultCode枚举类中 EXIST/SUCCESS/EXCEPTION/INVALID,具体的值参考ResultCode
	 */
	ResultCode add(UserAccount account);
	/**
	 * 更新
	 * @param account 传入用户帐号对象，Id的值不能为空
	 * @return 返回ResultCode枚举类中 EXIST/SUCCESS/EXCEPTION/INVALID,具体的值参考ResultCode
	 */
	ResultCode update(UserAccount account);
	/**
	 * 通过ID获得对象
	 * @param id 主键
	 * @return null or 对象值
	 */
	UserAccount get(String id);
	/**
	 * 更新，帐号不能重复
	 * @param account
	 * @return
	 */
	ResultCode edit(UserAccount account);
	
}
