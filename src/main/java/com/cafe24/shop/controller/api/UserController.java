package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//spring-servlet.xml에 스캔 패키지 등록
@RequestMapping("/api/user")
//기본 controller 패키지 내 usercontroller가 존재하므로 충돌 발생 가능 so /user/api 경로 명시
@RestController("userAPIController")
public class UserController {

	@Autowired
	private UserService userService;
	
//	@ResponseBody
	@ApiOperation(value="이메일 중복 체크")
	@ApiImplicitParams({
		@ApiImplicitParam(name="email", value="이메일 주소", required=true, dataType="string", defaultValue="")
	})
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		boolean exist = userService.existEmail(email);
		
		//JSON 형태로 변형하여 전송
		JSONResult result = JSONResult.success(exist);
		return result;
	}
	
}
