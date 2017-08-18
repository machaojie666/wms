package com._520it.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Role extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String sn;
	
	private List<Permission> permissions = new ArrayList<>();

	private List<SystemMenu> menus = new ArrayList<>();

	@Override
	public String toString() {
		return "Role [name=" + name + ", sn=" + sn + "]";
	}
	
}
