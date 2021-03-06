package com.cafe24.shop.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CartVO;
import com.cafe24.shop.vo.OptionVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//(회원) 장바구니 추가
	public boolean insert(CartVO cartVO) {
		return sqlSession.insert("cart.insert", cartVO)==1;
	}
	
	//(회원) 장바구니 목록
	public List<CartVO> selectAllByMemberId(CartVO cartVO) {
		return sqlSession.selectList("cart.selectAllByMemberId", cartVO);
	}
	
	//(회원) 장바구니 수정
	public boolean update(CartVO cartVO) {
		return sqlSession.update("cart.update", cartVO)==1;
	}
	
	//(회원) 장바구니 삭제
	public boolean delete(Long no) {
		return sqlSession.delete("cart.delete", no)==1;
	}
	
	//(회원) 장바구니 조회
	public CartVO selectByNo(Long no) {
		return sqlSession.selectOne("cart.selectByNo", no);
	}
	
}
