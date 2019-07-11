package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.CartVO;

@Service
public class OrderService {

	//상품 DB
	private static List<Long> productList = new ArrayList<>();
	//하위 옵션 DB
	private static List<Long> optionList = new ArrayList<>();
	//회원 DB
	private static List<String> memberList = new ArrayList<>();
	//장바구니 DB
	private static List<CartVO> cartList = new ArrayList<>();
	
	//초기화
	public void initLists() {
		for(Long i=1L;i<6L;i++) {
			productList.add(i);
			optionList.add(i);
		}
		memberList.add("test");
	}
	
	//test by 하드코딩
	//장바구니 담기
	public boolean 장바구니담기(String memberId, Long productNo,
							 Long secondOptionNo, Long cartAmount, Long cartPrice) {
		initLists();
		if(memberId!=null&&productNo!=null&&secondOptionNo!=null&&cartAmount!=null&&cartPrice!=null) {
			if(productList.contains(productNo)&&
					optionList.contains(secondOptionNo)&&
					memberList.contains(memberId)) {
				//장바구니 담기 완료
				cartList.add(new CartVO(memberId,productNo,secondOptionNo,cartAmount,cartPrice));
				return true;
			}
		}
		return false;
	}
	
	//test by 하드코딩
	//장바구니 조회
	public List<CartVO> 장바구니조회(String memberId) {
		List<CartVO> clist = new ArrayList<>();
		
		//현재 고객의 장바구니 정보 모두 추출
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getMemberId().equals(memberId)) {
				clist.add(cartList.get(i));
			}
		}
		return clist;
	}
	
}
