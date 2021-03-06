package com.cafe24.shop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.OptionVO;
import com.cafe24.shop.vo.ProductVO;

@Repository
public class OptionDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//(관리자) 옵션 추가
	public boolean insert(OptionVO optionVO) {
		return sqlSession.insert("option.insert", optionVO)==1;
	}
	
	//(관리자) 옵션 삭제
	public boolean delete(Long no) {
		return sqlSession.delete("option.delete", no)==1;
	}
	
	//(관리자) 상품별 옵션 목록
	public List<OptionVO> selectAllOptionByProductNo(Long productNo) {
		return sqlSession.selectList("option.selectAllOptionByProductNo", productNo);
	}
	
}
