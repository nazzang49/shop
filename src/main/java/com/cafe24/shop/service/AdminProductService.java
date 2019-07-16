package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.ProductVO;

//(관리자) 상품 서비스
@Service
public class AdminProductService {

	//상품 DB
	private static List<ProductVO> productTable = new ArrayList<>();
	
	//DB 초기화
	public void initTables() {
		productTable.clear();
		productTable.add(new ProductVO(1L, "productTest1", 20000L, "설명", "Y", 1L, 1L));
		productTable.add(new ProductVO(2L, "productTest2", 40000L, "설명", "Y", 2L, 2L));
	}
	
	//test by 하드코딩
	//상품 목록
	public List<ProductVO> 상품목록() {
		initTables();
		return productTable;
	}
	
	//test by 하드코딩
	//상품 추가
	public boolean 상품추가(ProductVO pvo) {
		if(pvo!=null) {
			return true;
		}
		return false;
	}
	
	//test by 하드코딩
	//상품 수정 >> 확인
	public List<ProductVO> 상품수정(ProductVO pvo) {
		initTables();
		if(pvo!=null) {
			for(int i=0;i<productTable.size();i++) {
				ProductVO tvo = productTable.get(i);
				if(tvo.getNo()==pvo.getNo()) {
					//DB와 비교 >> 수정 >> 수정된 리스트 리턴
					tvo.setName(pvo.getName());
					tvo.setPrice(pvo.getPrice());
					tvo.setShortDescription(pvo.getShortDescription());
					tvo.setAlignUse(pvo.getAlignUse());
					tvo.setAlignNo(pvo.getAlignNo());
					productTable.set(i, tvo);
					return productTable;
				}
			}
		}
		//수정 실패 DB = 기존 DB
		return productTable;
	}
	
	//test by 하드코딩
	//상품 삭제
	public boolean 상품삭제(Long no) {
		initTables();
		if(no!=null) {
			for(ProductVO tvo : productTable) {
				//table 정보 - 파라미터로 전달 받은 vo 정보 비교
				if(tvo.getNo()==no) return true;
			}
		}
		return false;
	}
	
}
