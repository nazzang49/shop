package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.AdminOrderService;
import com.cafe24.shop.vo.OrderVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//(관리자) 주문 컨트롤러
@RequestMapping("/api/adminorder")
@RestController("adminorderAPIController")
public class AdminOrderController {

	@Autowired
	private AdminOrderService adminOrderService;
	
	//주문  목록
	//@Auth(role=Auth.Role.ADMIN)
	@ApiOperation(value="주문 목록")
	@GetMapping(value="/list")
	public JSONResult getList() {
		
		
		//관리자 인증
		
		
		List<OrderVO> orderList = adminOrderService.주문목록();
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("orderList", orderList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//주문 상태 수정
	//@Auth(role=Auth.Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="주문 번호", required=true, dataType="path", defaultValue=""),
		@ApiImplicitParam(name="status", value="주문 상태", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="주문 상태 수정")
	@PutMapping(value="/update/{no}")
	public JSONResult udpate(@ModelAttribute @Valid OrderVO ovo,
							 BindingResult br) {
		//관리자 인증
		
		
		//valid
		if(br.hasErrors()) {
			return JSONResult.fail("입력 실패");
		}
		
		
		boolean flag = adminOrderService.주문상태수정(ovo);
	
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
