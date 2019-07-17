package com.cafe24.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafe24.shop.repository.MemberDAO;
import com.cafe24.shop.vo.MemberVO;

//회원
@Service
public class MemberService {

	@Autowired
	private MemberDAO userDao;
	
	//아이디 중복 체크
	public boolean 아이디중복체크(MemberVO memberVO) {
		MemberVO mvo = userDao.checkid(memberVO);
		//중복 O
		if(mvo!=null) return true;
		//중복 X
		return false;
	}
	
	//조인
	public boolean 조인(MemberVO memberVO) {
		return userDao.insert(memberVO);
	}
	
	//로그인
	public boolean 로그인(MemberVO memberVO) {
		MemberVO authUser = userDao.selectByIdAndPassword(memberVO);
		if(authUser!=null) return true;
		return false;
	}
	
	//회원조회
	public MemberVO 회원조회(MemberVO memberVO) {
		MemberVO mvo = userDao.selectById(memberVO);
		return mvo;
	}
	
	//회원수정
	public boolean 회원수정(MemberVO memberVO) {
		return userDao.update(memberVO);
	}
	
	//회원탈퇴
	public boolean 회원탈퇴(MemberVO memberVO) {
		return userDao.delete(memberVO);
	}
	
	//로그인 세션 >> 보류
	public MemberVO getUser(MemberVO vo) {
		MemberVO user = userDao.get(vo.getEmail(), vo.getPassword());
		return user;
	}
}
