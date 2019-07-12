package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.UserProductService;

import io.swagger.annotations.ApiOperation;

//(고객) 상품 컨트롤러
@RequestMapping("/api/product")
@RestController("productAPIController")
public class UserProductController {

	@Autowired
	private UserProductService productService;
	
	@ApiOperation(value="상품 목록")
	@GetMapping(value="/list")
	public JSONResult getList() {
		Map<String, Object> data = productService.상품목록();
		
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="상품 조회")
	@GetMapping(value="/detail/{no}")
	public JSONResult detail(@PathVariable(value="no") Long no) {
		boolean flag = productService.상품조회(no);
		
		JSONResult result = JSONResult.success(flag ? "상품 O" : "상품 X");
		return result;
	}	
	
}
