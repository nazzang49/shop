package com.cafe24.shop.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CartVO;
import com.cafe24.shop.vo.OptionVO;
import com.cafe24.shop.vo.OrderDetailVO;
import com.cafe24.shop.vo.OrderVO;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final String keyValue="shop-order";
	
	//(회원) 주문 추가
	public boolean insert(OrderVO orderVO) {
		orderVO.setKeyValue(keyValue);
		return sqlSession.insert("order.insert", orderVO)==1;
	}
	
	//(회원) 주문 상세 추가
	public boolean insertOrderDetail(CartVO cartVO) {
		return sqlSession.insert("order.insertOrderDetail", cartVO)==1;
	}
	
	//(회원) 주문 상세 목록 >> 수정 필요
	public List<CartVO> selectAllByMemberId(CartVO cartVO) {
		return sqlSession.selectList("cart.selectAllByMemberId", cartVO);
	}

	//(회원) 주문 번호
	public Long selectMaxOrderNo() {
		return sqlSession.selectOne("order.selectMaxOrderNo");
	}
	
}
