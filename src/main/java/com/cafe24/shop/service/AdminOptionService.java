package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.OptionVO;
import com.cafe24.shop.vo.ProductOptionVO;

//(관리자) 옵션 서비스
@Service
public class AdminOptionService {
	
	//옵션 DB
	private static List<OptionVO> optionTable = new ArrayList<>();
	
	//상품옵션 DB
	private static List<ProductOptionVO> productOptionTable = new ArrayList<>();
	
	//DB 초기화
	public void initTables() {
		optionTable.clear();
		productOptionTable.clear();
		
		//1번 상품에 대한 옵션 추가 >> 깊이 1
		optionTable.add(new OptionVO(1L, 1L, "블랙", 1L));
		//1번 상품에 대한 옵션 추가 >> 깊이 2
		optionTable.add(new OptionVO(2L, 1L, "화이트", 2L));
		
		//1번 상품의 1차 옵션 번호 = 1, 2차 옵션 번호 = 2
		productOptionTable.add(new ProductOptionVO(1L, 1L, 1L, 2L, 1000L, 930L));
		//1번 상품의 1차 옵션 번호 = 3, 2차 옵션 번호 = 4
		productOptionTable.add(new ProductOptionVO(2L, 1L, 3L, 4L, 1000L, 930L));
	}
	
	//test by 하드코딩
	//옵션 추가 >> 추가 확인
	public List<OptionVO> 옵션추가(OptionVO fvo) {
		initTables();
		if(fvo!=null) {
			optionTable.add(fvo);
			return optionTable;
		}
		return optionTable;
	}
	
	//test by 하드코딩
	//옵션 삭제
	public boolean 옵션삭제(Long no) {
		initTables();
		if(no!=null) {
			for(OptionVO tvo : optionTable) if(tvo.getNo()==no) return true;
		}
		return false;
	}
	
}

