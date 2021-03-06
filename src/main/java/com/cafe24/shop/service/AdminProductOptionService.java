package com.cafe24.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafe24.shop.repository.ProductOptionDAO;
import com.cafe24.shop.vo.ProductOptionVO;

//(관리자) 상품옵션 서비스
@Service
public class AdminProductOptionService {

	@Autowired
	private ProductOptionDAO productOptionDao;
	
	public boolean 상품옵션추가(List<Long> firstOptionNoList,
							 List<Long> secondOptionNoList,
							 List<Long> remainAmountList,
							 Long productNo) {
		
		boolean flag = true;
		
		for(int i=0;i<firstOptionNoList.size();i++) {
			flag = productOptionDao.insertProductOption(new ProductOptionVO(productNo,
																	 firstOptionNoList.get(i),
																	 secondOptionNoList.get(i),
																	 remainAmountList.get(i),
																	 remainAmountList.get(i)));
		}
		return flag;
	}
	
	public boolean 상품옵션삭제(List<Long> firstOptionNoList) {
		boolean flag = true;
		
		for(Long no : firstOptionNoList) {
			flag = productOptionDao.deleteProductOption(no);
		}
		return flag;
	}
}
