package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.UserProductService;
import com.cafe24.shop.vo.ProductVO;

import io.swagger.annotations.ApiOperation;

//(회원) 상품 컨트롤러
@RequestMapping("/api/product")
@RestController("productAPIController")
public class UserProductController {

	@Autowired
	private UserProductService userProductService;

	//상품 목록
	@ApiOperation(value="상품 목록")
	@GetMapping(value="/list/{categoryNo}")
	public JSONResult getList(ProductVO productVO) {
		
		//map 생성
		
		if(productVO.getCategoryNo()==null) {
			//메인 상품 목록
			
		}else {
			//특정 카테고리 내 상품 목록
			
		}
		
		
		
		
		
		
		Map<String, Object> data = userProductService.상품목록();
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//상품 상세
	@ApiOperation(value="상품 상세")
	@GetMapping(value="/detail/{no}")
	public JSONResult detail(@ModelAttribute ProductVO productVO) {
		
		boolean flag = userProductService.상품상세(productVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}	
	
}
