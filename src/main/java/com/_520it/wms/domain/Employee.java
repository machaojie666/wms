package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Employee extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;
	private Department dept;
	
	private List<Role> roles = new ArrayList<>();

	public String getRoleNames(){
		StringBuilder sb = new StringBuilder();
		if (roles.size() == 0) {
			return "[暂未分配角色]";
		} else {
			sb.append("[");
			for (Role role : roles) {
				sb.append(role.getName()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", password="
				+ password + ", email=" + email + ", age=" + age + ", admin="
				+ admin + "]";
	}
	
}
