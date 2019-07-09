package com.cafe24.shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.GuestbookService;
import com.cafe24.shop.vo.GuestBookVO;
import com.google.common.base.Optional;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/guestbook")
@RestController("guestbookAPIController")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ApiOperation(value="방명록 리스트 조회")
	@RequestMapping(value="/list/{no}", method=RequestMethod.GET)
	public JSONResult list(@PathVariable(value="no") Long no) {
		GuestBookVO gvo = guestbookService.get(no);
				
		//JSON 형태로 변형하여 전송
		JSONResult result = JSONResult.success(gvo);
		return result;
	}
	
}
