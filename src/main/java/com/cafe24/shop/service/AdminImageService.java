package com.cafe24.shop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafe24.shop.repository.ImageDAO;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.ProductVO;

//(관리자) 이미지 서비스
@Service
public class AdminImageService {

	@Autowired
	private ImageDAO imageDao;
	
	//이미지 추가
	public boolean 이미지추가(ImageVO imageVO) {
		//여러개일 경우, for문으로 쿼리 실행 반복
		return imageDao.insert(imageVO);
	}
	
	//이미지 삭제
	public boolean 이미지삭제(ImageVO imageVO) {
		return imageDao.delete(imageVO);
	}
	
	//메인 상품 썸네일 목록 >> 비진열 포함
	public List<ImageVO> 썸네일() {
		return imageDao.selectAllThumbnail();
	}
	
	//특정 카테고리 내 상품 썸네일 목록 >> 비진열 포함
	public List<ImageVO> 썸네일(ProductVO productVO) {
		return imageDao.selectAllThumbnailByCategoryNo(productVO);
	}
	
}
