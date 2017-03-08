package com.bugframework.common.annotation;
/**
 * 用于时间字段查询，查询该时间的时间段，比如查询ct，那么标记注解后，最要查询开始和结束时间
 * @author Administrator
 *
 */
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD,java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface DateColumn {

}
