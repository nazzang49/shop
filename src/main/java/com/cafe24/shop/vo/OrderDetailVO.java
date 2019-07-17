package com.cafe24.shop.vo;

public class OrderDetailVO {

	private Long no;
	private Long orderNo;
	private Long productOptionNo;
	//상품별 주문 수량
	private Long orderAmount;
	//상품별 주문 금액
	private Long orderPrice;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getProductOptionNo() {
		return productOptionNo;
	}
	public void setProductOptionNo(Long productOptionNo) {
		this.productOptionNo = productOptionNo;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Long getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Long orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@Override
	public String toString() {
		return "OrderDetailVO [no=" + no + ", orderNo=" + orderNo + ", productOptionNo=" + productOptionNo
				+ ", orderAmount=" + orderAmount + ", orderPrice=" + orderPrice + "]";
	}
	
}
