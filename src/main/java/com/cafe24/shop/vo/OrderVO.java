package com.cafe24.shop.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderVO {
	
	//pk
	private Long no;
	private String memberId;
	@NotEmpty
	private String customerName;
	@NotEmpty
	private String customerAddress;
	@NotEmpty
	private String customerPhone;
	@NotEmpty
	private String customerEmail;
	@NotEmpty
	private String receiverName;
	@NotEmpty
	private String receiverAddress;
	@NotEmpty
	private String receiverPhone;
	@NotEmpty
	private String receiverMsg;
	private String orderDate;
	@NotEmpty
	private String paymentCategory;
	@NotNull
	private Long paymentPrice;
	private String status;
	//선택 주문 by 체크박스 >> 장바구니 번호 리스트
	private List<Long> cartNoList;
	//회원 이름
	private String memberName;
	//암복호화 키값
	private String keyValue;
	
	public OrderVO() {
		
	}
	
	public OrderVO(Long no, String memberId, String customerName, String customerAddress, String customerPhone,
			 	   String customerEmail, String receiverName, String receiverAddress, String receiverPhone,
			 	   String receiverMsg, String orderDate, Long paymentPrice, String status) {
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
	
	public String getPaymentCategory() {
		return paymentCategory;
	}
	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public List<Long> getCartNoList() {
		return cartNoList;
	}
	public void setCartNoList(List<Long> cartNoList) {
		this.cartNoList = cartNoList;
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
	public Long getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(Long paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	
	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", memberId=" + memberId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", customerEmail=" + customerEmail
				+ ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", receiverPhone="
				+ receiverPhone + ", receiverMsg=" + receiverMsg + ", orderDate=" + orderDate + ", paymentCategory="
				+ paymentCategory + ", paymentPrice=" + paymentPrice + ", status=" + status + ", cartNoList="
				+ cartNoList + ", memberName=" + memberName + ", keyValue=" + keyValue + "]";
	}
}
