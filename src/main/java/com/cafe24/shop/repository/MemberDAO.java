package com.cafe24.shop.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.shop.vo.MemberVO;
import com.cafe24.shop.vo.OrderVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//암복호화 키값
	private final String keyValue = "shop-keyValue";
	
	//아이디 중복 체크
	public MemberVO checkid(MemberVO memberVO) {
		return sqlSession.selectOne("member.checkid", memberVO);
	}
	
	//조인
	public boolean insert(MemberVO memberVO) {
		memberVO.setKeyValue(keyValue);
		return sqlSession.insert("member.insert", memberVO)==1;
	}
	
	//로그인
	public MemberVO selectByIdAndPassword(MemberVO memberVO) {
		memberVO.setKeyValue(keyValue);
		return sqlSession.selectOne("member.selectByIdAndPassword", memberVO);
	}
	
	//회원조회
	public MemberVO selectById(MemberVO memberVO) {
		memberVO.setKeyValue(keyValue);
		return sqlSession.selectOne("member.selectById", memberVO);
	}
		
	//회원수정
	public boolean update(MemberVO memberVO) {
		memberVO.setKeyValue(keyValue);
		return sqlSession.update("member.update", memberVO)==1;
	}
	
	//회원탈퇴
	public boolean delete(MemberVO memberVO) {
		memberVO.setKeyValue(keyValue);
		return sqlSession.delete("member.delete", memberVO)==1;
	}
	
	//(관리자) 회원 목록 by 검색
	public List<MemberVO> selectBySearch(String searchType, String searchKwd) {
		Map<String, String> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("searchKwd", searchKwd);
		map.put("keyValue", keyValue);
		return sqlSession.selectList("member.selectBySearch", map);
	}
	
	//(관리자) 회원 주문 목록 by 검색
	public List<OrderVO> selectOrderBySearch(String searchType, String searchKwd) {
		Map<String, String> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("searchKwd", searchKwd);
		map.put("keyValue", keyValue);
		return sqlSession.selectList("member.selectOrderBySearch", map);
	}
	
	//(관리자) 회원삭제 = 강제탈퇴
	public boolean deleteByAdmin(String id) {
		return sqlSession.delete("member.deleteByAdmin", id)==1;
	}
	
	//로그인하는 사용자의 세션값 저장을 위한 정보 추출
	public MemberVO get(String email, String pw) {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("pw", pw);
		MemberVO vo = sqlSession.selectOne("user.getByEmailAndPw",map);
		return vo;
	}
}