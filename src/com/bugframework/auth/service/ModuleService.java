package com.bugframework.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.auth.pojo.Module;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;

/**
 * 
 * <p>Title:模块服务接口层 </p>
 * <p>Description: 模块服务接口层</p>
 * @author 许增飞
 * @date 2016-8-14 下午12:24:58
 */
public interface ModuleService {
	/**
	 * 获得已经启用而且未被删除的模块列表
	 * 
	 * @param delFlag
	 *            是否删除 1--是 0--否
	 * @param isenabled
	 *            是否启用 1--是 0--否
	 * @return List<AuthModule>
	 */
	List<Module> find(int delFlag, int isenabled);

	/**
	 * 获得未被删除的模块，按orderBy属性顺序排序,创建时间倒序排序
	 * 
	 * @return List<AuthModule>
	 */
	List<Module> find();

	/**
	 * 通过名字和上级模块ID 查询列表
	 * 
	 * @param name
	 *            名字
	 * @param pid
	 *            上级模块ID
	 * @return List<AuthModule>
	 */
	List<Module> find(String name, String pid);

	/**
	 * 分页查询
	 * 
	 * @param module
	 * @param datagrid
	 * @param request
	 */
	void datagrid(Module module, DataGrid<Module> datagrid,
                  HttpServletRequest request);

	/**
	 * 通过主键获得模块对象
	 * 
	 * @param id
	 *            主键ID
	 * @return 返回 AuthModule
	 */
	Module get(String id);

	/**
	 * 更新模块
	 * 
	 * @param s
	 *            传入对象
	 */
	ResultCode update(Module s);

	/**
	 * 添加模块
	 * 
	 * @param module
	 *            传入模块值
	 */
	ResultCode add(Module module);

	/**
	 * 逻辑批量删除
	 * 
	 * @param id
	 *            传入主键ID，多个ID以逗号隔开
	 */
	void deleteAlllogic(String id);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 *            传入主键ID
	 */
	void deletelogic(String id);
	/**
	 * 批量获取
	 * @param ids  传入id数组
	 * @return
	 */
	List<Module> get(String[] ids);
	

}
