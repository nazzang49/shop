package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.CategoryVO;

//(관리자) 카테고리 서비스
@Service
public class AdminCategoryService {

	//카테고리 DB
	private static List<CategoryVO> categoryTable = new ArrayList<>();
	static int number = 0;
	
	//DB 초기화
	public void initTables() {
		number = 1;
		categoryTable.clear();
		categoryTable.add(new CategoryVO(1L, "category1"));
		categoryTable.add(new CategoryVO(2L, "category2"));
	}
	
	//test by 하드코딩
	//카테고리 목록
	public List<CategoryVO> 카테고리목록() {
		initTables();
		return categoryTable;
	}
	
	//test by 하드코딩
	//카테고리 추가
	public boolean 카테고리추가(CategoryVO cvo) {
		if(cvo!=null) {
			return true;
		}
		return false;
	}
	
	//test by 하드코딩
	//카테고리 수정
	public boolean 카테고리수정(CategoryVO cvo) {
		initTables();
		if(cvo!=null) {
			for(CategoryVO tvo : categoryTable) {
				//table 정보 - 파라미터로 전달 받은 vo 정보 비교
				if(tvo.getNo()==cvo.getNo()) return true;
			}
		}
		return false;
	}
	
	//test by 하드코딩
	//카테고리 삭제
	public boolean 카테고리삭제(Long no) {
		initTables();
		if(no!=null) {
			for(CategoryVO tvo : categoryTable) {
				if(tvo.getNo()==no) return true;
			}
		}
		return false;
	}
	
}
