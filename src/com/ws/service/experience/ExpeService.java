package com.ws.service.experience;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.experience.Experience;

public interface ExpeService {
	/**
	 * 添加
	 * @param expe 添加的对象值
	 * @return ResultCode  
	 */
	ResultCode add(Experience expe);
	/**
	 * 更新
	 * @param expe 更新对象
	 * @return ResultCode  
	 */
	ResultCode edit(Experience expe);

	/**
	 * 获得对象
	 * @param id 传入主键
	 * @return  Experience值
	 */
	Experience get(String id);
    /**
     * 分页查询
     *
     * @param expe  荣誉证书对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Experience expe, DataGrid<Experience> datagrid, HttpServletRequest request);

	/**
	 *  删除（逻辑删除，包括删除图片）
	 * @param expe
	 * @return
	 */
	ResultCode del(Experience expe);
}
