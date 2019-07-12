package com.cafe24.shop.vo;

public class FirstOptionVO {

	private Long no;
	private Long productNo;
	private String name;
	
	public FirstOptionVO() {

	}
	
	public FirstOptionVO(Long no, Long productNo, String name) {
		this.no=no;
		this.productNo=productNo;
		this.name=name;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "FirstOptionVO [no=" + no + ", productNo=" + productNo + ", name=" + name + "]";
	}
}
