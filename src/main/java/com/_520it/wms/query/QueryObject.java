package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

public class QueryObject {
	@Getter@Setter
	private Integer currentPage = 1;
	@Getter@Setter
	private Integer pageSize = 5;
	
	public Integer getBeginIndex(){
		return (currentPage - 1) * pageSize;
	}

	public String empty2null(String str){
		return str != null && !"".equals(str.trim())?str:null;
	}

}
