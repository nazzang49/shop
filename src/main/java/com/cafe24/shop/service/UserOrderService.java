package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shop.repository.CartDAO;
import com.cafe24.shop.repository.OrderDAO;
import com.cafe24.shop.repository.ProductDAO;
import com.cafe24.shop.vo.CartVO;
import com.cafe24.shop.vo.OrderDetailVO;
import com.cafe24.shop.vo.OrderVO;

//(회원) 주문 서비스
@Service
public class UserOrderService {

	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private CartDAO cartDao;
	
	//주문 추가 >> 장바구니 번호 >> insert 주문 상세
//	@Transactional
	public boolean 주문추가(OrderVO orderVO) {
		boolean flag = true;
		//1. 주문 추가 >> 주문자, 수령자 개인정보 암호화
		orderDao.insert(orderVO);
		Long orderNo = orderDao.selectMaxOrderNo();
		
		System.out.println("리턴된 주문 번호 : "+orderNo);
		System.out.println("장바구니 사이즈 : "+orderVO.getCartNoList().size());
		
		//2. 주문 상세 추가
		for(Long cartNo : orderVO.getCartNoList()) {
			CartVO cartVO = cartDao.selectByNo(cartNo);
			cartVO.setOrderNo(orderNo);
					
			flag = orderDao.insertOrderDetail(cartVO);
		}
		
		return flag;
	}
	
	//주문 상세 목록 by 회원 아이디
	public List<OrderDetailVO> 주문상세(OrderVO orderVO) {
		return null;
	}
	
}
