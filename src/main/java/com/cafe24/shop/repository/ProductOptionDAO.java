package com.cafe24.shop.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.shop.vo.ProductOptionVO;

@Repository
public class ProductOptionDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//(관리자) 상품옵션 추가
	public boolean insertProductOption(ProductOptionVO productOptionVO) {
		return sqlSession.insert("productOption.insertProductOption", productOptionVO)==1;
	}
	
	//(관리자) 상품옵션 삭제
	public boolean deleteProductOption(Long no) {
		return sqlSession.delete("productOption.deleteProductOption", no)==1;
	}
	
}
