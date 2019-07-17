package com.cafe24.shop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.shop.vo.ProductVO;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//(관리자) 상품 추가
	public Long insert(ProductVO productVO) {
		return (long)sqlSession.insert("product.insert", productVO);
	}
	
	//(관리자) 진열 순서
	public Long selectMaxAlignNo(ProductVO productVO) {
		return sqlSession.selectOne("product.selectMaxAlignNo", productVO);
	}
	
	//(관리자) 메인 상품 목록
	public List<ProductVO> selectAll() {
		return sqlSession.selectList("product.selectAll");
	}
	
	//(관리자) 특정 카테고리 내 상품 목록
	public List<ProductVO> selectAllByCategoryNo(ProductVO productVO) {
		return sqlSession.selectList("product.selectAllByCategoryNo", productVO);
	}
	
	//(관리자) 상품 수정
	public boolean update(ProductVO productVO) {
		return sqlSession.update("product.update", productVO)==1;
	}
	
	//(관리자) 상품 삭제
	public boolean delete(ProductVO productVO) {
		return sqlSession.update("product.delete", productVO)==1;
	}
	
	//(고객) 상품 목록
	
	//(고객 및 관리자) 상품 상세
	
}
