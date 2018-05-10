package com.sooncode.apipath.node;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Node {
 

	/**
	 * 节点类型: controller ; service ; dao
	 * 
	 * @return
	 */
	NodeType type();

	/**
	 * 解释说明
	 * 
	 * @return
	 */
	String explain();

	/**
	 * 关键信息
	 * 
	 * @return
	 */
	String key();

	 
	
	 
}
