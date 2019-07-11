package com.cafe24.shop.vo;

//일단, 기본 정보만 테스트
public class MemberVO {
	
	//pk
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String auth;
	private String regDate;
	
	//기본 생성자 >> set, get 메소드 사용하는 경우
	public MemberVO() {
		
	}
	
	//임의 생성자 >> 객체 생성 시 즉시 초기화
	public MemberVO(String id, String password, String name, String address
					, String phone, String email, String auth, String regDate) {
		this.id=id;
		this.password=password;
		this.name=name;
		this.address=address;
		this.phone=phone;
		this.email=email;
		this.auth=auth;
		this.regDate=regDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", auth=" + auth + ", regDate=" + regDate + "]";
	}
}
