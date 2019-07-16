package com.cafe24.shop.repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.shop.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
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
	
//	
//	public MemberVO get(Long no) {
//		MemberVO vo = sqlSession.selectOne("user.getByNo",no);
//		return vo;
//	}
//	
//	public MemberVO get(String email) {
//		MemberVO vo = sqlSession.selectOne("user.getByEmail",email);
//		return vo;
//	}
//	
	//로그인하는 사용자의 세션값 저장을 위한 정보 추출
	public MemberVO get(String email, String pw) {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("pw", pw);
		MemberVO vo = sqlSession.selectOne("user.getByEmailAndPw",map);
		return vo;
	}
}