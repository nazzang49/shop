package com.cafe24.shop.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.MemberService;
import com.cafe24.shop.vo.MemberVO;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/member")
@RestController("memberAPIController")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value="회원가입")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(@RequestBody MemberVO mvo) {
		boolean flag = memberService.회원가입(mvo);
		
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
	@ApiOperation(value="아이디중복체크")
	@RequestMapping(value="/checkid", method=RequestMethod.POST)
	public JSONResult checkid(@RequestBody Map<String, String> map) {
		boolean flag = memberService.아이디중복체크((String)map.get("id"));
		
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
	@ApiOperation(value="로그인")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login(@RequestBody Map<String, String> map) {
		boolean flag = memberService.로그인((String)map.get("id"),(String)map.get("password"));
		
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
	//회원정보조회
	@ApiOperation(value="회원정보조회")
	@RequestMapping(value="/info/{id}", method=RequestMethod.GET)
	public JSONResult info(@PathVariable(value="id") String id) {
		MemberVO mvo = memberService.회원정보조회(id);
		
		JSONResult result = JSONResult.success(mvo);
		return result;
	}
	
	@ApiOperation(value="회원정보수정")
	@RequestMapping(value="/modify", method=RequestMethod.PUT)
	public JSONResult modify(@RequestBody MemberVO mvo) {
		boolean flag = memberService.회원정보수정(mvo);
		
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
	@ApiOperation(value="회원탈퇴")
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public JSONResult delete(@RequestBody Map<String, String> map) {
		boolean flag = memberService.회원탈퇴((String)map.get("password"));
		
		JSONResult result = JSONResult.success(flag);
		return result;
	}
}
