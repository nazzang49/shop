package com.cafe24.shop.controller.api;

import java.util.HashMap;
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
	
	//조인 >> 회원 user / 관리자 admin
	@ApiOperation(value="조인")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(@RequestBody MemberVO mvo) {
		
		boolean flag = memberService.조인(mvo);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//아이디 중복 체크
	@ApiOperation(value="아이디 중복 체크")
	@RequestMapping(value="/checkid", method=RequestMethod.POST)
	public JSONResult checkid(@RequestBody Map<String, String> map) {
		
		boolean flag = memberService.아이디중복체크((String)map.get("id"));
		
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//로그인
	@ApiOperation(value="로그인")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login(@RequestBody Map<String, String> map) {
		
		boolean flag = memberService.로그인((String)map.get("id"),(String)map.get("password"));
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//회원조회
	@ApiOperation(value="회원조회")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public JSONResult get(@PathVariable(value="id") String id) {
		
		//본인 인증
		
		MemberVO mvo = memberService.회원조회(id);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("mvo", mvo);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//회원수정
	@ApiOperation(value="회원수정")
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public JSONResult update(@RequestBody MemberVO mvo,
							 @PathVariable(value="id") String id) {
		
		//본인 인증
		
		boolean flag = memberService.회원수정(id, mvo);
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	@ApiOperation(value="회원탈퇴")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public JSONResult delete(@RequestBody Map<String, String> map,
							 @PathVariable(value="id") String id) {
		
		//본인 인증
		
		boolean flag = memberService.회원탈퇴((String)map.get("password"));
		
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
}
