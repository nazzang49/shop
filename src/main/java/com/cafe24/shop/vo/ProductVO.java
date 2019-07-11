package com.cafe24.shop.vo;

//일단, 기본 정보만 테스트
public class ProductVO {
	
	//상품 기본 정보
	//pk
	private Long no;
	private String name;
	private int price;
	private String shortDescription;
	private String alignUse;
	private Long alignNo;
	
	//상품별 카테고리 정보 >> 조인 실시
	private Long firstCategoryNo;
	private Long secondCategoryNo;
	private Long thirdCategoryNo;
	
	public ProductVO() {
		
	}
	
	public ProductVO(Long no, String name, int price, String shortDescription, String alignUse, Long alignNo,
					 Long firstCategoryNo, Long secondCategoryNo, Long thirdCategoryNo) {
		
		this.no=no;
		this.name=name;
		this.price=price;
		this.shortDescription=shortDescription;
		this.alignUse=alignUse;
		this.alignNo=alignNo;
		this.firstCategoryNo=firstCategoryNo;
		this.secondCategoryNo=secondCategoryNo;
		this.thirdCategoryNo=thirdCategoryNo;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getAlignUse() {
		return alignUse;
	}
	public void setAlignUse(String alignUse) {
		this.alignUse = alignUse;
	}
	public Long getAlignNo() {
		return alignNo;
	}
	public void setAlignNo(Long alignNo) {
		this.alignNo = alignNo;
	}
	public Long getFirstCategoryNo() {
		return firstCategoryNo;
	}
	public void setFirstCategoryNo(Long firstCategoryNo) {
		this.firstCategoryNo = firstCategoryNo;
	}
	public Long getSecondCategoryNo() {
		return secondCategoryNo;
	}
	public void setSecondCategoryNo(Long secondCategoryNo) {
		this.secondCategoryNo = secondCategoryNo;
	}
	public Long getThirdCategoryNo() {
		return thirdCategoryNo;
	}
	public void setThirdCategoryNo(Long thirdCategoryNo) {
		this.thirdCategoryNo = thirdCategoryNo;
	}
	
	@Override
	public String toString() {
		return "ProductVO [no=" + no + ", name=" + name + ", price=" + price + ", shortDescription=" + shortDescription
				+ ", alignUse=" + alignUse + ", alignNo=" + alignNo + ", firstCategoryNo=" + firstCategoryNo
				+ ", secondCategoryNo=" + secondCategoryNo + ", thirdCategoryNo=" + thirdCategoryNo + "]";
	}
}
