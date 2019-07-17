package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.CartVO;
import com.cafe24.shop.vo.OrderDetailVO;
import com.cafe24.shop.vo.OrderVO;

//(회원) 주문 서비스
@Service
public class UserOrderService {

	//상품 DB
	private static List<Long> productTable = new ArrayList<>();
	//하위 옵션 DB
	private static List<Long> optionTable = new ArrayList<>();
	//회원 DB
	private static List<String> memberTable = new ArrayList<>();
	//장바구니 DB
	private static List<String> cartTable = new ArrayList<>();
	
	//DB 초기화
	public void initTables() {
		productTable.add(1L);
		productTable.add(2L);
		productTable.add(3L);
		
		optionTable.add(1L);
		optionTable.add(2L);
		optionTable.add(3L);
		
		memberTable.add("test");
		cartTable.add("test");
	}
	
	//test by 하드코딩
	//장바구니 추가
	public boolean 장바구니추가(CartVO cvo) {
		initTables();
		if(cvo!=null) {
			if(optionTable.contains(cvo.getProductOptionNo())&&memberTable.contains(cvo.getMemberId())) return true;
		}
		return false;
	}
	
	//test by 하드코딩
	//장바구니 목록
	public List<String> 장바구니목록(CartVO cvo) {
		return null;
	}
	
	//장바구니수량수정
	public boolean 장바구니수량수정(CartVO cvo) {
		return true;
	}
	
	//장바구니삭제
	public boolean 장바구니삭제(CartVO cvo) {
		return true;
	}
	
	//주문 추가
	public boolean 주문추가(OrderVO orderVO) {
		//1. 장바구니 정보 조회
		List<Long> cartNoList = orderVO.getCartNoList();
		
		return true;
	}
	
	//주문 목록 by 회원 아이디
	public List<OrderDetailVO> 주문상세(OrderVO orderVO) {
		return null;
	}
	
}
