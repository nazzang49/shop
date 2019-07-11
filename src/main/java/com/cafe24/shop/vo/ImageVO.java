package com.cafe24.shop.vo;

//일단, 기본 정보만 테스트
public class ImageVO {

	private Long no;
	//상품 번호
	private Long productNo;
	//이미지 경로
	private String url;
	//썸네일 or 기본 구분
	private String repOrBasic;
	private String regDate;
	
	public ImageVO() {
		
	}
	
	public ImageVO(Long no, Long productNo, String url, String repOrBasic, String regDate) {
		this.no=no;
		this.productNo=productNo;
		this.url=url;
		this.repOrBasic=repOrBasic;
		this.regDate=regDate;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRepOrBasic() {
		return repOrBasic;
	}
	public void setRepOrBasic(String repOrBasic) {
		this.repOrBasic = repOrBasic;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "ImageVO [no=" + no + ", productNo=" + productNo + ", url=" + url + ", repOrBasic=" + repOrBasic
				+ ", regDate=" + regDate + "]";
	}
}
