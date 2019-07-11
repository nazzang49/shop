package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.ProductVO;

import ch.qos.logback.core.joran.action.NewRuleAction;

@Service
public class ProductService {

	//상품번호 리스트 >> 실제 DB 대체
	private static List<Long> productNoList = new ArrayList<>();
	
	//초기화
	public void initProductNoList() {
		//5개의 상품 보유
		productNoList.add(1L);
		productNoList.add(2L);
		productNoList.add(3L);
		productNoList.add(4L);
		productNoList.add(5L);
	}
	
	//test by 하드코딩
	//상품 전체 목록 >> 상품 정보 + 썸네일 이미지
	public Map<String, Object> 상품전체목록() {
		Map<String, Object> map = new HashMap<>();
		
		List<ProductVO> productList = new ArrayList<>();
		List<ImageVO> imageList = new ArrayList<>();
		
		//DB에 입력되었다고 가정한 상품 정보
		productList.add(new ProductVO(1L,"반팔",35000,"기능성 티셔츠","Y",1L,1L,2L,3L));
		imageList.add(new ImageVO(1L,1L,"/image/shop-uploads/test.png","R","2018-10-01"));
		
		map.put("productList", productList);
		map.put("imageList", imageList);
		
		return map;
	}
	
	//test by 하드코딩
	//상품 상세 조회 >> 상품 디테일 정보
	public boolean 상품상세조회(Long no) {
		initProductNoList();
		if(productNoList.contains(no)) return true;
		return false;
	}
	
}
