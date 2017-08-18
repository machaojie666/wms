package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class EmployeeQueryObject extends QueryObject {
	
	private String keywords;
	
	private Long deptId;
	
	
}
