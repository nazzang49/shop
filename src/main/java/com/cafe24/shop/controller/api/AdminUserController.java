package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.AdminUserService;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.MemberVO;
import com.cafe24.shop.vo.OrderVO;

import io.swagger.annotations.ApiOperation;

//(관리자) 회원 컨트롤러
@RequestMapping("/api/admin/user")
@RestController("adminUserAPIController")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	@ApiOperation(value="회원 목록")
	@GetMapping(value="/list")
	public JSONResult getUserList(@RequestParam(value="searchType") String searchType,
							  @RequestParam(value="searchKwd") String searchKwd) {
		
		//관리자 인증
		
		//valid by JS
		
		List<MemberVO> userList = adminUserService.회원목록(searchType, searchKwd);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("userList", userList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="회원 삭제")
	@DeleteMapping(value="/delete")
	public JSONResult deleteUser(@RequestParam(value="id") List<String> userIdList) {
		
		//관리자 인증
		
		//valid by JS
		
		boolean flag = adminUserService.회원삭제(userIdList);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="회원 주문 목록")
	@GetMapping(value="/order/list")
	public JSONResult getUserOrderList(@RequestParam(value="searchType") String searchType,
									   @RequestParam(value="searchKwd") String searchKwd) {
		
		//관리자 인증
		
		//valid by JS
		
		List<OrderVO> userOrderList = adminUserService.회원주문목록(searchType, searchKwd);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("userOrderList", userOrderList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
