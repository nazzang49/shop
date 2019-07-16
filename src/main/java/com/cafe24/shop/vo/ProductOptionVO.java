package com.cafe24.shop.vo;

import javax.validation.constraints.NotNull;

public class ProductOptionVO {

	private Long no;
	@NotNull
	private Long productNo;
	//1차 옵션
	@NotNull
	private Long firstOption;
	//2차 옵션
	@NotNull
	private Long secondOption;
	//재고수량 >> 비재고의 경우, -1
	@NotNull
	private Long remainAmount;
	//판매가능수량 >> 비재고의 경우, -1
	@NotNull
	private Long availableAmount;
	
	public ProductOptionVO() {
		
	}
	
	public ProductOptionVO(Long no, Long productNo, Long firstOption, Long secondOption, Long remainAmount, Long availableAmount) {
		
		this.no=no;
		this.productNo=productNo;
		this.firstOption=firstOption;
		this.secondOption=secondOption;
		this.remainAmount=remainAmount;
		this.availableAmount=availableAmount;
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
	public Long getFirstOption() {
		return firstOption;
	}
	public void setFirstOption(Long firstOption) {
		this.firstOption = firstOption;
	}
	public Long getSecondOption() {
		return secondOption;
	}
	public void setSecondOption(Long secondOption) {
		this.secondOption = secondOption;
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
		return "ProductOptionVO [no=" + no + ", productNo=" + productNo + ", firstOption=" + firstOption
				+ ", secondOption=" + secondOption + ", remainAmount=" + remainAmount + ", availableAmount="
				+ availableAmount + "]";
	}
		
}
