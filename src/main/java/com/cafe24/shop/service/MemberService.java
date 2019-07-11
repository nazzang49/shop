package com.cafe24.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafe24.shop.repository.MemberDAO;
import com.cafe24.shop.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO userDao;
	
	//test by 하드코딩
	//아이디 중복 체크
	public boolean 아이디중복체크(String id) {
		if(id!=null) return true;
		return false;
	}
	
	//test by 하드코딩
	//회원가입
	public boolean 회원가입(MemberVO mvo) {
		if(mvo!=null) return true;
		return false;
	}
	
	//test by 하드코딩
	//로그인
	public boolean 로그인(String id, String password) {
		if(id!=null&&password!=null) return true;
		return false;
	}
	
	//test by 하드코딩
	//회원 정보 수정
	public MemberVO 회원정보조회(String id) {
		MemberVO mvo = new MemberVO("test","pw","test","서울","010-1111-1111"
									,"test@naver.com","USER","2018-10-01");
			
		return mvo;
	}
	
	//test by 하드코딩
	//회원 정보 수정
	public boolean 회원정보수정(MemberVO mvo) {
		if(mvo!=null) return true;
		return false;
	}
	
	//test by 하드코딩
	//회원 탈퇴 = 삭제
	public boolean 회원탈퇴(String password) {
		if(password!=null) return true;
		return false;
	}
	
	//로그인 세션 >> 보류
	public MemberVO getUser(MemberVO vo) {
		MemberVO user = userDao.get(vo.getEmail(), vo.getPassword());
		return user;
	}
}
