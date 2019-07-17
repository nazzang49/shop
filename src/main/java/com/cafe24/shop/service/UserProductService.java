package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.ImageDAO;
import com.cafe24.shop.repository.ProductDAO;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.ProductVO;

//(회원) 상품 서비스
@Service
public class UserProductService {
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private ImageDAO imageDao;
	
	//상품 목록
	public Map<String, Object> 상품목록() {
		
		//전체 or 카테고리 번호
		
		return null;
	}
	
	//test by 하드코딩
	//상품 상세
	public boolean 상품상세(ProductVO prodcutVO) {
		return true;
	}
	
}
