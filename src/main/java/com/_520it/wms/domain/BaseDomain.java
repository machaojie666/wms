package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter@Setter@ToString
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Long id;
}
