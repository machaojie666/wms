package com._520it.wms.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 此注解贴在方法上
@Retention(RetentionPolicy.RUNTIME) // 可以保存在JVM中
public @interface RequiredPermission {
	
	// 权限的名称
	String value();
	
}
