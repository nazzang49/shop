package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.FirstOptionVO;
import com.cafe24.shop.vo.SecondOptionVO;

//(관리자) 옵션 서비스
@Service
public class AdminOptionService {
	
	//옵션 DB
	private static List<FirstOptionVO> firstOptionTable = new ArrayList<>();
	private static List<SecondOptionVO> secondOptionTable = new ArrayList<>();
	
	//DB 초기화
	public void initTables() {
		firstOptionTable.clear();
		secondOptionTable.clear();
		
		//1번 상품에 대한 2개의 상위 옵션
		firstOptionTable.add(new FirstOptionVO(1L, 1L, "색상"));
		firstOptionTable.add(new FirstOptionVO(2L, 1L, "사이즈"));
		
		//1번 상품의 2개의 상위 옵션에 대한 하위 옵션 + 재고 + 판매 가능  수량
		secondOptionTable.add(new SecondOptionVO(1L, "블랙", 1000L, 930L));
		secondOptionTable.add(new SecondOptionVO(1L, "L", 1000L, 930L));
	}
	
	//test by 하드코딩
	//상위 옵션 추가 >> 추가 확인
	public List<FirstOptionVO> 상위옵션추가(FirstOptionVO fvo) {
		initTables();
		if(fvo!=null) {
			firstOptionTable.add(fvo);
			System.out.println("상위 옵션 테이블 사이즈 : "+firstOptionTable.size());
			return firstOptionTable;
		}
		return firstOptionTable;
	}
	
	//test by 하드코딩
	//상위 옵션 삭제
	public boolean 상위옵션삭제(Long no) {
		initTables();
		if(no!=null) {
			for(FirstOptionVO tvo : firstOptionTable) if(tvo.getNo()==no) return true;
		}
		return false;
	}
	
}

