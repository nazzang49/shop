package com.cafe24.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

//일단, 기본 정보만 테스트
public class CategoryVO {

	private Long no;
	@NotEmpty
	private String name;
	
	public CategoryVO() {
		
	}
	
	public CategoryVO(Long no, String name) {
		this.no=no;
		this.name=name;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CategoryVO [no=" + no + ", name=" + name + "]";
	}
	
}
