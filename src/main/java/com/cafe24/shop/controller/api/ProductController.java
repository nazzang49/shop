package com.cafe24.shop.controller.api;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/product")
@RestController("productAPIController")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@ApiOperation(value="상품전체목록")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public JSONResult list() {
		Map<String, Object> map = productService.상품전체목록();
		
		JSONResult result = JSONResult.success(map);
		return result;
	}
	
	//@GetMapping >> 보다 더 RESTful 한 형태를 위해
	@ApiOperation(value="상품상세조회")
	@GetMapping(value="/detail/{no}")
	public JSONResult detail(@PathVariable(value="no") Long no) {
		boolean flag = productService.상품상세조회(no);
		
		JSONResult result = JSONResult.success(flag ? "상품 O" : "상품 X");
		return result;
	}	
	
}
