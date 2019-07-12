package com.cafe24.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

//일단, 기본 정보만 테스트
public class OrderVO {
	
	//pk
	private Long no;
	private String memberId;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	private String customerEmail;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private String receiverMsg;
	private String orderDate;
	private int paymentPrice;
	@NotEmpty
	private String status;
	
	public OrderVO() {
		
	}
	
	public OrderVO(Long no, String memberId, String customerName, String customerAddress, String customerPhone,
			 	   String customerEmail, String receiverName, String receiverAddress, String receiverPhone,
			 	   String receiverMsg, String orderDate, int paymentPrice, String status) {
		this.no=no;
		this.memberId=memberId;
		this.customerName=customerName;
		this.customerAddress=customerAddress;
		this.customerPhone=customerPhone;
		this.customerEmail=customerEmail;
		this.receiverName=receiverName;
		this.receiverAddress=receiverAddress;
		this.receiverPhone=receiverPhone;
		this.orderDate=orderDate;
		this.receiverMsg=receiverMsg;
		this.paymentPrice=paymentPrice;
		this.status=status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverMsg() {
		return receiverMsg;
	}
	public void setReceiverMsg(String receiverMsg) {
		this.receiverMsg = receiverMsg;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	
	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", memberId=" + memberId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", customerEmail=" + customerEmail
				+ ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", receiverPhone="
				+ receiverPhone + ", receiverMsg=" + receiverMsg + ", orderDate=" + orderDate + ", paymentPrice="
				+ paymentPrice + ", status=" + status + "]";
	}
}
