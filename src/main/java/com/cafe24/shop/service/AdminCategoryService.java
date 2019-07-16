package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.CategoryDAO;
import com.cafe24.shop.vo.CategoryVO;

//(관리자) 카테고리 서비스
@Service
public class AdminCategoryService {

	@Autowired
	private CategoryDAO cateogryDao;
	
	//test by 하드코딩
	//카테고리 목록
	public List<CategoryVO> 카테고리목록() {
		return cateogryDao.selectAll();
	}
	
	//카테고리 추가
	public boolean 카테고리추가(CategoryVO categoryVO) {
		return cateogryDao.insert(categoryVO);
	}
	
	//카테고리 수정
	public boolean 카테고리수정(CategoryVO categoryVO) {
		return cateogryDao.update(categoryVO);
	}
	
	//카테고리 삭제
	public boolean 카테고리삭제(Long no) {
		return cateogryDao.delete(no);
	}
	
}
