package com.cafe24.shop.controller.api;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.AdminCategoryService;
import com.cafe24.shop.vo.CategoryVO;
import com.cafe24.shop.vo.MemberVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//(관리자) 카테고리 컨트롤러
@RequestMapping("/api/admin/category")
@RestController("adminCategoryAPIController")
public class AdminCategoryController {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AdminCategoryService adminCategoryService;
	
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
	
	@ApiOperation(value="카테고리 추가")
	@PostMapping(value="/add")
	public ResponseEntity<JSONResult> add(@ModelAttribute @Valid CategoryVO categoryVO,
						  				  BindingResult br) {
		
		//관리자 인증
		
		//valid
		if(br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError error : errorList) {
				String msg = error.getDefaultMessage();
				JSONResult result = JSONResult.fail(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);	
			}
		}
			
		boolean flag = adminCategoryService.카테고리추가(categoryVO);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@ApiOperation(value="카테고리 수정")
	@PutMapping(value="/update/{no}")
	public ResponseEntity<JSONResult> udpate(@ModelAttribute CategoryVO categoryVO,
							 				 BindingResult br) {
		//관리자 인증
		
		
		//valid >> 아이디, 비밀번호 2개 입력값 >> MemberVO에서 로그인 시 필요하지 않은 데이터 별도 처리 필요 or 에러 발생
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<CategoryVO>> validatorResults = validator.validateProperty(categoryVO, "name");
		
		if(!validatorResults.isEmpty()) {
			for(ConstraintViolation<CategoryVO> validatorResult : validatorResults) {
				String msg = messageSource.getMessage("NotEmpty.categoryVO.name", null, LocaleContextHolder.getLocale());
				JSONResult result = JSONResult.fail(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
		}
		
		
		boolean flag = adminCategoryService.카테고리수정(categoryVO);
	
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@ApiOperation(value="카테고리 삭제")
	@DeleteMapping(value="/delete/{no}")
	public JSONResult delete(@ModelAttribute CategoryVO categoryVO) {
		
		//관리자 인증
		
		boolean flag = adminCategoryService.카테고리삭제(categoryVO);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
