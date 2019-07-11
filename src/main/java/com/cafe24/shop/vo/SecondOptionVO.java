package com.cafe24.shop.vo;

public class SecondOptionVO {

	private Long no;
	//상위 옵션 종류
	private Long firstOptionNo;
	private String name;
	//재고수량
	private Long remainAmount;
	//판매가능수량
	private Long availableAmount;
	private String optionUse;
	
	public SecondOptionVO() {
		
	}
	
	public SecondOptionVO(Long no, Long firstOptionNo, String name,
						  Long remainAmount, Long availableAmount, String optionUse) {
		
		this.no=no;
		this.firstOptionNo=firstOptionNo;
		this.name=name;
		this.remainAmount=remainAmount;
		this.availableAmount=availableAmount;
		this.optionUse=optionUse;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public String getOptionUse() {
		return optionUse;
	}
	public void setOptionUse(String optionUse) {
		this.optionUse = optionUse;
	}

	@Override
	public String toString() {
		return "SecondOptionVO [no=" + no + ", firstOptionNo=" + firstOptionNo + ", name=" + name + ", remainAmount="
				+ remainAmount + ", availableAmount=" + availableAmount + ", optionUse=" + optionUse + "]";
	}
		
}
