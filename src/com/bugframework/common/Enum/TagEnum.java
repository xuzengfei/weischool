package com.bugframework.common.Enum;
 

/**
 * 标签的枚举类
 * @author 许增飞
 *
 */
public enum TagEnum {
	/**
	 * 操作标记
	 */
	OPTION("opt","opt"),
	/**
	 * 添加按钮
	 */
	ADD("add","&#xe600;"),
	/**
	 * 删除按钮
	 */
	DEL("del","&#xe6e2;"),
	/**
	 * 编辑按钮
	 */
	EDIT("edit","&#xe6df;"),
	/**
	 * 禁用按钮
	 */
	DISABLED("disabled","&#xe631;"),
	/**
	 * 启用按钮
	 */
	ENABLED("enabled","&#xe615;");
	 
	
	private String key;
	private String val;
	
	private TagEnum(String key,String val){
		this.key =key;
		this.val=val;
	}
	/**
	 * 通过传入的key返回枚举的值
	 * @param key 传入的KEY
	 * @return 如果找到则返回枚举的值，否则返回空
	 */
	public static String  getVal(String key){
		if(key!=null){
			for(TagEnum t:TagEnum.values()){
				if (t.getKey() == key) {
                    return t.getVal();
                }
			}
		}
		return null;
	}
	/**
	 * 通过传入的key返回枚举的值
	 * @param key 传入的KEY
	 * @return 如果找到则返回枚举的值，否则返回原来的值
	 */
	public static String  getVal1(String key){
		if(key!=null){
			for(TagEnum t:TagEnum.values()){
				if (t.getKey() == key) {
                    return t.getVal();
                }
			}
		}
		return key;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
