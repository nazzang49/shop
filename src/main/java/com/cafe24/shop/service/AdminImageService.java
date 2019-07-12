package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shop.vo.ImageVO;

//(관리자) 이미지 서비스
@Service
public class AdminImageService {

	//상품 DB
	private static List<ImageVO> imageTable = new ArrayList<>();
	
	//DB 초기화
	public void initTables() {
		imageTable.clear();
		imageTable.add(new ImageVO(1L, 1L, "/image/shop-uploads/test.png", "R", "2018-10-01"));
		imageTable.add(new ImageVO(2L, 1L, "/image/shop-uploads/test.png", "B", "2018-10-01"));
	}
	
	//test by 하드코딩
	//이미지 추가 >> 추가 확인
	public List<ImageVO> 이미지추가(ImageVO ivo) {
		initTables();
		if(ivo!=null) {
			imageTable.add(ivo);
			return imageTable;
		}
		return imageTable;
	}
	
	//test by 하드코딩
	//이미지 추가 >> 추가 확인
	public boolean 이미지삭제(Long no) {
		initTables();
		if(no!=null) {
			for(ImageVO tvo : imageTable) {
				if(tvo.getNo()==no) return true;
			}
		}
		return false;
	}
	
}
