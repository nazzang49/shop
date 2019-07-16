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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.UserOrderService;
import com.cafe24.shop.vo.CartVO;

import io.swagger.annotations.ApiOperation;

//(고객) 주문 컨트롤러
@RequestMapping("/api/order")
@RestController("orderAPIController")
public class UserOrderController {

	@Autowired
	private UserOrderService orderService;
	
	@ApiOperation(value="장바구니 추가")
	@PostMapping(value="/cart/add")
	public ResponseEntity<JSONResult> add(@RequestBody @Valid CartVO cartVO,
						  				  BindingResult br) {
		
		//본인 인증
		
		//valid
		if(br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError error : errorList) {
				String msg = error.getDefaultMessage();
				//fail >> 에러 메시지 전송
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
	
	@ApiOperation(value="장바구니 조회")
	@GetMapping(value="/cart/{memberId}")
	public JSONResult getList(@PathVariable(value="memberId") String memberId) {

		//본인 인증
		
		List<String> categoryList = orderService.장바구니조회(memberId);
		
		Map<String, Object> data = new HashMap<>();
		data.put("categoryList", categoryList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
