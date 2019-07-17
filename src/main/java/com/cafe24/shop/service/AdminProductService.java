package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.ProductDAO;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.ProductVO;

//(관리자) 상품 서비스
@Service
public class AdminProductService {

	@Autowired
	private ProductDAO productDao;
	
	//메인 상품 목록 >> 비진열 포함
	public List<ProductVO> 상품목록() {
		return productDao.selectAll();
	}
	
	//특정 카테고리 내 상품 목록 >> 비진열 포함
	public List<ProductVO> 상품목록(ProductVO productVO) {
		return productDao.selectAllByCategoryNo(productVO);
	}
	
	//상품 추가
	public Long 상품추가(ProductVO productVO) {
		return productDao.insert(productVO);
	}
	
	//상품 수정
	public boolean 상품수정(ProductVO productVO) {
		return productDao.update(productVO);
	}
	
	//상품 삭제 >> 진열번호 update
	public boolean 상품삭제(ProductVO productVO) {
		return productDao.delete(productVO);
	}
	
	//진열 번호
	public Long 진열번호(ProductVO productVO) {
		return productDao.selectMaxAlignNo(productVO);
	}
	
	//진열번호수정
	
	
}
