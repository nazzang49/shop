package com.cafe24.shop.vo;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import com.cafe24.shop.validator.constraints.ValidName;

public class MemberVO {
	
	@NotEmpty
	private String id;
	//비밀번호 >> 영문, 특수기호, 숫자 혼합
	@Pattern(regexp="(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}", message="비밀번호는 8자 이상 20자 이하의 알파벳, 숫자, 특수문자를 조합하여 작성해야 합니다.") 
	@Length(min=8, max=20)
	private String password;
	//사용자 정의 valid 적용 가능
	//@ValidName
	@NotEmpty
	private String name;
	@NotEmpty
	private String address;
	@NotEmpty
	private String phone;
	@Email
	@NotEmpty
	private String email;
	private String role;
	private String regDate;
	//암복호화 지정 키값
	private String keyValue;
	
	//기본 생성자
	public MemberVO() {
		
	}
	
	public MemberVO(String id, String password, String name, String address, String phone, String email, String role, String regDate) {
		this.id=id;
		this.password=password;
		this.name=name;
		this.address=address;
		this.phone=phone;
		this.email=email;
		this.role=role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", role=" + role + ", regDate=" + regDate + "]";
	}
}
