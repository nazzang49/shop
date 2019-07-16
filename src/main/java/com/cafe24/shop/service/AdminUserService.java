package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cafe24.shop.vo.MemberVO;

//(관리자) 회원 서비스
@Service
public class AdminUserService {

	//회원 DB
	private static List<MemberVO> memberTable = new ArrayList<>();
	
	//DB 초기화
	public void initTables() {
		memberTable.clear();
		memberTable.add(new MemberVO("test1", "jy@park2@@", "박진영", "서울", "010-1111-1111", "test1@naver.com", "USER", "2018-10-01"));
		memberTable.add(new MemberVO("test2", "jy@park2@@", "박진수", "서울", "010-1111-1111", "test2@naver.com", "USER", "2018-10-01"));
	}
	
	//test by 하드코딩
	//회원 목록
	public List<MemberVO> 회원목록() {
		initTables();
		return memberTable;
	}
	
	//test by 하드코딩
	//회원 삭제
	public boolean 회원삭제(String id) {
		initTables();
		for(MemberVO tvo : memberTable) {
			if(tvo.getId().equals(id)) return true;
		}
		return false;
	}
	
}
