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
	private DataSource datasource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberDAO() {
		System.out.println("UserDAO 생성자");
	}
	
	//회원추가(세션 팩토리)
	public boolean insert(MemberVO vo) {
		return sqlSession.insert("user.insert", vo)==1;
	}
	
	//회원정보 변경
	public boolean update(MemberVO vo) {
		return sqlSession.update("user.update", vo)==1;
	}
	
	public MemberVO get(Long no) {
		MemberVO vo = sqlSession.selectOne("user.getByNo",no);
		return vo;
	}
	
	public MemberVO get(String email) {
		MemberVO vo = sqlSession.selectOne("user.getByEmail",email);
		return vo;
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