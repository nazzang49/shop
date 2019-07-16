package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.CartVO;

//(고객) 주문 서비스
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
	//장바구니 담기
	public boolean 장바구니추가(CartVO cvo) {
		initTables();
		if(cvo!=null) {
			if(optionTable.contains(cvo.getProductOptionNo())&&memberTable.contains(cvo.getMemberId())) return true;
		}
		return false;
	}
	
	//test by 하드코딩
	//장바구니 조회
	public List<String> 장바구니조회(String memberId) {
		initTables();
		List<String> categoryList = new ArrayList<>();
		
		//현재 고객 장바구니 리스트
		if(memberId!=null) {
			if(cartTable.contains(memberId)) {
				categoryList.add(memberId);
			}
		}
		return categoryList;
	}
	
}
