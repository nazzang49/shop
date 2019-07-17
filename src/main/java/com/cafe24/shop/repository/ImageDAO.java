package com.cafe24.shop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.ProductVO;

@Repository
public class ImageDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//(관리자) 이미지 추가
	public boolean insert(ImageVO imageVO) {
		return sqlSession.insert("image.insert", imageVO)==1;
	}
	
	//(관리자) 이미지 삭제
	public boolean delete(ImageVO imageVO) {
		return sqlSession.delete("image.delete", imageVO)==1;
	}
	
	//(관리자) 메인 상품 썸네일 목록
	public List<ImageVO> selectAllThumbnail() {
		return sqlSession.selectList("image.selectAllThumbnail");
	}
	
	//(관리자) 특정 카테고리 내 상품 썸네일 목록
	public List<ImageVO> selectAllThumbnailByCategoryNo(ProductVO productVO) {
		return sqlSession.selectList("image.selectAllThumbnailByCategoryNo", productVO);
	}
	
}
