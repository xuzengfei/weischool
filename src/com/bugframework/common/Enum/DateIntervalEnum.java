package com.bugframework.common.Enum;
 

/**
 * 时间区间标记
 * @author 许增飞
 *
 */
public enum DateIntervalEnum {
	START("ST"),
	END("ED");
	private String val;
	
	private DateIntervalEnum(String val){
 
		this.val=val;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
	  return this.val;
	}

}
