package com.ws.pojo.grade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.bugframework.common.pojo.IdEntity;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.DateUtils;

/**
 * 课程班次时间表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "ws_cs_t")
public class GradeTime extends IdEntity {
	/**
	 * 班级ID
	 */
	private String gradId;
	/**
	 * 开始时间
	 */
	private Long start;
	/**
	 * 结束时间
	 */
	private Long end;

	private String date;

		private String gradName;

	@Column(name = "gra_id")
	public String getGradId() {
		return gradId;
	}
	public void setGradId(String gradId) {
		this.gradId = gradId;
	}
	@Column(name = "start")
	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	@Column(name = "end")
	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}
	@Transient
	public String getDate(){
		if (this.start!=null){
			return DateUtils.timestapTostr(this.start,"d");
		}
		return  null;
	}
	@Transient
	public String getGradName() {
		return gradName;
	}

	public void setGradName(String gradName) {
		this.gradName = gradName;
	}
}
