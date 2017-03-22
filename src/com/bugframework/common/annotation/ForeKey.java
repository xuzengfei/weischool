package com.bugframework.common.annotation;
/**
 * 指定对象外键
 * 比如需要查询account对象中角色，在一个get方法那里加个@ForeKey(category="sysRole",column="id")  
 * @author Administrator
 *
 */
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD,java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ForeKey {
	/**
	 * 外键的“对象实例”名称
	 * @return
	 */
	 public String category() default "";
	 /**
	  * 外键“对象属性名称”
	  * @return
	  */
	 public String column() default "";

	/**
	 * 是否为主键
	 * 如果是主键的话查询就不要模糊来查
	 * @return
	 */
	 public boolean id() default true;
}
