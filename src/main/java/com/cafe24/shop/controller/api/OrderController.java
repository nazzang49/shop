package com.cafe24.shop.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.OrderService;
import com.cafe24.shop.vo.CartVO;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/order")
@RestController("orderAPIController")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="장바구니담기")
	@PostMapping(value="/cart/add")
	public JSONResult add(@RequestBody Map<String, Object> map) {
		boolean flag = orderService.장바구니담기((String)map.get("memberId"),
											  ((Integer)map.get("productNo")).longValue(),
											  ((Integer)map.get("secondOptionNo")).longValue(),
											  ((Integer)map.get("cartAmount")).longValue(),
											  ((Integer)map.get("cartPrice")).longValue());
		
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
	@ApiOperation(value="장바구니조회")
	@GetMapping(value="/cart/{memberId}")
	public JSONResult list(@PathVariable(value="memberId") String memberId) {
		List<CartVO> clist = orderService.장바구니조회(memberId);
		
		JSONResult result = JSONResult.success(clist);
		return result;
	}
	
}
