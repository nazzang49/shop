package com.cafe24.shop.vo;

//일단, 기본 정보만 테스트
public class CartVO {

	private String memberId;
	private Long productNo;
	private Long secondOptionNo;
	//상품별 수량
	private Long cartAmount;
	//상품별 합계금액
	private Long cartPrice;
		
	public CartVO() {
		
	}
	
	public CartVO(String memberId, Long productNo, Long secondOptionNo, Long cartAmount, Long cartPrice) {
		this.memberId=memberId;
		this.productNo=productNo;
		this.secondOptionNo=secondOptionNo;
		this.cartAmount=cartAmount;
		this.cartPrice=cartPrice;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public Long getSecondOptionNo() {
		return secondOptionNo;
	}
	public void setSecondOptionNo(Long secondOptionNo) {
		this.secondOptionNo = secondOptionNo;
	}
	public Long getCartAmount() {
		return cartAmount;
	}
	public void setCartAmount(Long cartAmount) {
		this.cartAmount = cartAmount;
	}
	public Long getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(Long cartPrice) {
		this.cartPrice = cartPrice;
	}
	
	@Override
	public String toString() {
		return "CartVO [memberId=" + memberId + ", productNo=" + productNo + ", secondOptionNo=" + secondOptionNo
				+ ", cartAmount=" + cartAmount + ", cartPrice=" + cartPrice + "]";
	}
	
}
