package com.bugframework.common.utility;

public enum ResultCode {
	/**
	 * 已经存在
	 */
	EXIST(-2),
	/**
	 * 成功
	 */
	SUCCESS(1),
	/**
	 * 异常
	 */
	EXCEPTION(-1),
	/**
	 * 失败
	 */
	FAIL(0),
	/**
	 * 数据无效
	 */
	INVALID(-3),
	/***
	 * 不存在
	 */
	NOEXIST(-4)
	;
	private int code;

	private ResultCode(int code) {
		this.code = code;
	}

	public int getValue() {
		return this.code;
	}

}
