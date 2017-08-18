package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Permission extends BaseDomain {
	private static final long serialVersionUID = 1L;
	// 权限名称
	private String name;
	// 权限表达式
	private String expression;
	
}
