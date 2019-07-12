package com.cafe24.shop.vo;

public class SecondOptionVO {

	//상위 옵션 번호
	private Long firstOptionNo;
	//하위 옵션 내용
	private String name;
	//재고수량
	private Long remainAmount;
	//판매가능수량
	private Long availableAmount;
	
	public SecondOptionVO() {
		
	}
	
	public SecondOptionVO(Long firstOptionNo, String name,
						  Long remainAmount, Long availableAmount) {
		
		this.firstOptionNo=firstOptionNo;
		this.name=name;
		this.remainAmount=remainAmount;
		this.availableAmount=availableAmount;
	}

	public Long getFirstOptionNo() {
		return firstOptionNo;
	}
	public void setFirstOptionNo(Long firstOptionNo) {
		this.firstOptionNo = firstOptionNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(Long remainAmount) {
		this.remainAmount = remainAmount;
	}
	public Long getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(Long availableAmount) {
		this.availableAmount = availableAmount;
	}

	@Override
	public String toString() {
		return "SecondOptionVO [firstOptionNo=" + firstOptionNo + ", name=" + name + ", remainAmount=" + remainAmount
				+ ", availableAmount=" + availableAmount + "]";
	}
		
}
