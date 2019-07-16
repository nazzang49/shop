package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.ProductVO;

//(고객) 상품 서비스
@Service
public class UserProductService {

	//상품번호 리스트 >> 실제 DB 대체
	private static List<Long> productTable = new ArrayList<>();
	
	//초기화
	public void initTables() {
		productTable.clear();
		productTable.add(1L);
		productTable.add(2L);
		productTable.add(3L);
		productTable.add(4L);
		productTable.add(5L);
	}
	
	//test by 하드코딩
	//상품 목록 >> 상품 + 썸네일
	public Map<String, Object> 상품목록() {
		Map<String, Object> map = new HashMap<>();
		List<ProductVO> productList = new ArrayList<>();
		List<ImageVO> imageList = new ArrayList<>();
		
		//DB 입력 가정
		productList.add(new ProductVO(1L, "반팔", 35000L, "기능성 티셔츠", "Y", 1L, 1L));
		imageList.add(new ImageVO(1L, 1L, "/image/shop-uploads/test.png", "R","2018-10-01"));
		
		map.put("productList", productList);
		map.put("imageList", imageList);
		
		return map;
	}
	
	//test by 하드코딩
	//상품 상세
	public boolean 상품조회(Long no) {
		initTables();
		if(productTable.contains(no)) return true;
		return false;
	}
	
}
