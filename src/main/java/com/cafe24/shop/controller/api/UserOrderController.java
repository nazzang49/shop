package com.cafe24.shop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.UserOrderService;
import com.cafe24.shop.vo.CartVO;
import com.cafe24.shop.vo.OrderDetailVO;
import com.cafe24.shop.vo.OrderVO;

import io.swagger.annotations.ApiOperation;

//(회원) 주문 컨트롤러
@RequestMapping("/api/order")
@RestController("orderAPIController")
public class UserOrderController {

	@Autowired
	private UserOrderService orderService;
	
	@ApiOperation(value="장바구니 추가")
	@PostMapping(value="/cart/add")
	public ResponseEntity<JSONResult> cartAdd(@ModelAttribute @Valid CartVO cartVO,
											  BindingResult br) {
		
		//본인 인증
		
		//valid
		if(br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError error : errorList) {
				String msg = error.getDefaultMessage();
				JSONResult result = JSONResult.fail(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);	
			}
		}
		
		boolean flag = orderService.장바구니추가(cartVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@ApiOperation(value="장바구니 목록")
	@GetMapping(value="/cart/{memberId}")
	public JSONResult getCartList(@ModelAttribute CartVO cartVO) {

		//본인 인증
		
		
		//수정
		
		List<String> categoryList = orderService.장바구니목록(cartVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("categoryList", categoryList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="장바구니 수량 수정")
	@PutMapping(value="/cart/update/{no}")
	public JSONResult cartUpdate(@ModelAttribute CartVO cartVO) {
		
		//본인 인증
		
		//수량 min and max valid
		
		boolean flag = orderService.장바구니수량수정(cartVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="장바구니 삭제")
	@DeleteMapping(value="/cart/delete/{no}")
	public JSONResult cartDelete(@ModelAttribute CartVO cartVO) {
		
		//본인 인증
		
		boolean flag = orderService.장바구니삭제(cartVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="주문 추가")
	@PostMapping(value="/add")
	public ResponseEntity<JSONResult> orderAdd(@ModelAttribute @Valid OrderVO orderVO,
											   BindingResult br) {
		
		//본인 인증
		
		//valid
		if(br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError error : errorList) {
				String msg = error.getDefaultMessage();
				JSONResult result = JSONResult.fail(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
		}
		
		boolean flag = orderService.주문추가(orderVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@ApiOperation(value="주문 상세")
	@GetMapping(value="/list/{memberId}")
	public JSONResult getOrderDetailList(@ModelAttribute OrderVO orderVO) {

		//본인 인증
		
		List<OrderDetailVO> orderDetailList = orderService.주문상세(orderVO);
		
		Map<String, Object> data = new HashMap<>();
		data.put("orderDetailList", orderDetailList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//주문 취소
	
}
