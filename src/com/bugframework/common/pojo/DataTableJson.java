package com.bugframework.common.pojo;

import java.util.List;

/**
 * 
 * <p>
 * Title:datatables的请求数据源
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 许增飞
 * @date 2016-10-16 上午9:20:41
 */
public class DataTableJson {
	/**
	 * 过滤前总记录数
	 */
	private Integer recordsTotal;
	/**
	 * 过滤后总记录数
	 */
	private Integer recordsFiltered;
	/**
	 * 页面发来的参数，原样返回
	 */
	private String sEcho;
	/**
	 * 数据
	 */
	private List data;

	/**
	 * 获取过滤前总记录数
	 * 
	 * @return recordsTotal 过滤前总记录数
	 */
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * 设置过滤前总记录数
	 * 
	 * @param recordsTotal
	 *            过滤前总记录数
	 */
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * 获取过滤后总记录数
	 * 
	 * @return recordsFiltered 过滤后总记录数
	 */
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * 设置过滤后总记录数
	 * 
	 * @param recordsFiltered
	 *            过滤后总记录数
	 */
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * 获取页面发来的参数，原样返回
	 * 
	 * @return sEcho 页面发来的参数，原样返回
	 */
	public String getsEcho() {
		return sEcho;
	}

	/**
	 * 设置页面发来的参数，原样返回
	 * 
	 * @param sEcho
	 *            页面发来的参数，原样返回
	 */
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * 获取数据
	 * 
	 * @return aaData 数据
	 */

	public List getData() {
		return data;
	}

	/**
	 * 设置数据
	 * 
	 * @param aaData
	 *            数据
	 */

	public void setData(List data) {
		this.data = data;
	}

}
