package com.cafe24.shop.controller.api;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.AdminCategoryService;
import com.cafe24.shop.vo.CategoryVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//(관리자) 카테고리 컨트롤러
@RequestMapping("/api/admincategory")
@RestController("admincategoryAPIController")
public class AdminCategoryController {

	@Autowired
	private AdminCategoryService adminCategoryService;
	
	//카테고리  목록
	//@Auth(role=Auth.Role.ADMIN)
	@ApiOperation(value="카테고리 목록")
	@GetMapping(value="/list")
	public JSONResult getList() {
		
		
		//관리자 인증
		
		
		List<CategoryVO> categoryList = adminCategoryService.카테고리목록();
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("categoryList", categoryList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//카테고리  추가
	//@Auth(role=Auth.Role.ADMIN) >> 추후 인증 처리
	@ApiImplicitParams({
		@ApiImplicitParam(name="name", value="카테고리 이름", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="카테고리 추가")
	@PostMapping(value="/add")
	public JSONResult add(@ModelAttribute @Valid CategoryVO cvo,
						  BindingResult br) {
		
		//valid
		if(br.hasErrors()) {
			return JSONResult.fail("입력 실패");
		}
				
		
		//관리자 인증
		
		
		boolean flag = adminCategoryService.카테고리추가(cvo);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//카테고리  수정
	//@Auth(role=Auth.Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="카테고리 번호", required=true, dataType="path", defaultValue=""),
		@ApiImplicitParam(name="name", value="카테고리 이름", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="카테고리 수정")
	@PutMapping(value="/update/{no}")
	public JSONResult udpate(@ModelAttribute @Valid CategoryVO cvo,
							 BindingResult br) {
		//관리자 인증
		
		
		//valid
		if(br.hasErrors()) {
			return JSONResult.fail("입력 실패");
		}
		
		
		boolean flag = adminCategoryService.카테고리수정(cvo);
	
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//카테고리  삭제
	//@Auth(role=Auth.Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="카테고리 번호", required=true, dataType="path", defaultValue="")
	})
	@ApiOperation(value="카테고리 삭제")
	@DeleteMapping(value="/delete/{no}")
	public JSONResult delete(@PathVariable(value="no") Long no) {
		//관리자 인증
		
		
		boolean flag = adminCategoryService.카테고리삭제(no);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
