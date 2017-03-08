package com.ws.pojo.grade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;
/**
 * 时间设置
 * @author 许增飞
 *
 */
@Entity
@Table(name="ws_time")
public class TimeInfo extends IdEntity  {
	/**
	 * 显示时间标题：如12:30-14:30
	 */
	private String title;
	
	@Column(name = "title",length=50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	 
}
