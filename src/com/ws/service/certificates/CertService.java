package com.ws.service.certificates;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.certificates.Certificates;
/**
 * 荣誉证书服务接口层
 * @author Administrator
 *
 */
public interface CertService {
	/**
	 * 添加（把背景图片也添加进去）
	 * @param cert 添加的对象值
	 * @return ResultCode  
	 */
	ResultCode add(Certificates cert);
	/**
	 * 更新
	 * @param cert 更新对象
	 * @return ResultCode  
	 */
	ResultCode edit(Certificates cert);
	/**
	 * 获得对象
	 * @param id 主键
	 * @return Certificates值
	 */
	Certificates get(String id);
    /**
     * 分页查询
     *
     * @param cert  荣誉证书对象值
     * @param datagrid 分页模型
     * @param request  HttpServletRequest 请求
     */
    void datagrid(Certificates cert, DataGrid<Certificates> datagrid, HttpServletRequest request);

	/**
	 * 删除（逻辑删除，包括删除图片）
	 * @param cert
	 * @return
	 */
    ResultCode del(Certificates cert);
}
