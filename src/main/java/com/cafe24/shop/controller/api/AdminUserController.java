package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.AdminUserService;
import com.cafe24.shop.vo.MemberVO;

import io.swagger.annotations.ApiOperation;

//(관리자) 회원 컨트롤러
@RequestMapping("/api/adminuser")
@RestController("adminuserAPIController")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	//회원 목록
	@ApiOperation(value="회원 목록")
	@GetMapping(value="/list")
	public JSONResult getList() {
		
		
		//관리자 인증
		
		
		List<MemberVO> userList = adminUserService.회원목록();
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("userList", userList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//회원 삭제
	@ApiOperation(value="회원 삭제")
	@DeleteMapping(value="/delete/{id}")
	public JSONResult delete(@PathVariable(value="id") String id) {
		
		
		//관리자 인증
		
		
		boolean flag = adminUserService.회원삭제(id);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
