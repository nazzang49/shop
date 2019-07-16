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
import com.cafe24.shop.service.AdminImageService;
import com.cafe24.shop.service.AdminOptionService;
import com.cafe24.shop.service.AdminOrderService;
import com.cafe24.shop.service.AdminProductService;
import com.cafe24.shop.vo.CategoryVO;
import com.cafe24.shop.vo.OptionVO;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.MemberVO;
import com.cafe24.shop.vo.OrderVO;
import com.cafe24.shop.vo.ProductVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//(관리자) 상품 컨트롤러
@RequestMapping("/api/adminproduct")
@RestController("adminproductAPIController")
public class AdminProductController {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AdminProductService adminProductService;
	
	@Autowired
	private AdminImageService adminImageService;
	
	@Autowired
	private AdminOptionService adminOptionService;
	
	//상품 목록
	@ApiOperation(value="상품 목록")
	@GetMapping(value="/list")
	public JSONResult getList() {
		
		
		//관리자 인증
		
		
		List<ProductVO> productList = adminProductService.상품목록();
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("productList", productList);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//상품 추가
	@ApiImplicitParams({
		@ApiImplicitParam(name="name", value="상품명", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="categoryNo", value="카테고리 번호", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="price", value="상품가격", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="shortDescription", value="상품간단설명", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="alignUse", value="진열구분", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="alignNo", value="진열순서", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="상품 추가")
	@PostMapping(value="/add")
	public ResponseEntity<JSONResult> add(@ModelAttribute @Valid ProductVO pvo,
						  				  BindingResult br) {
		
		//valid
		if(br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError error : errorList) {
				String msg = error.getDefaultMessage();
				JSONResult result = JSONResult.fail(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);	
			}
		}
				
		
		//관리자 인증
		
		
		boolean flag = adminProductService.상품추가(pvo);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	//상품 수정 >> 상품명, 상품가격
	@ApiImplicitParams({
		@ApiImplicitParam(name="name", value="상품명", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="price", value="상품가격", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="shortDescription", value="상품간단설명", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="alignUse", value="진열구분", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="alignNo", value="진열순서", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="상품 수정")
	@PutMapping(value="/update/{no}")
	public ResponseEntity<JSONResult> udpate(@ModelAttribute @Valid ProductVO pvo,
							 				 BindingResult br) {
		//관리자 인증
		
		
		//valid
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ProductVO>> validatorResults = validator.validateProperty(pvo, "name");
		//카테고리 번호 수정 제외
		validatorResults.addAll(validator.validateProperty(pvo, "price"));
		validatorResults.addAll(validator.validateProperty(pvo, "shortDescription"));
		validatorResults.addAll(validator.validateProperty(pvo, "alignUse"));
		validatorResults.addAll(validator.validateProperty(pvo, "alignNo"));
		
		if(!validatorResults.isEmpty()) {
			String msg = "";
			for(ConstraintViolation<ProductVO> validatorResult : validatorResults) {
				if("name".equals(validatorResult.getPropertyPath().toString())) {
					msg = messageSource.getMessage("NotEmpty.pvo.name", null, LocaleContextHolder.getLocale());
					break;
				}else if("price".equals(validatorResult.getPropertyPath().toString())) {
					msg = messageSource.getMessage("NotNull.pvo.price", null, LocaleContextHolder.getLocale());
					break;
				}else if("shortDescription".equals(validatorResult.getPropertyPath().toString())) {
					msg = messageSource.getMessage("NotEmpty.pvo.shortDescription", null, LocaleContextHolder.getLocale());
					break;
				}else if("alignUse".equals(validatorResult.getPropertyPath().toString())) {
					msg = messageSource.getMessage("NotEmpty.pvo.alignUse", null, LocaleContextHolder.getLocale());
					break;
				}else {
					msg = messageSource.getMessage("NotNull.pvo.alignNo", null, LocaleContextHolder.getLocale());
					break;
				}
				
			}
			JSONResult result = JSONResult.fail(msg);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		
	
		List<ProductVO> productList = adminProductService.상품수정(pvo);
	
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("productList", productList);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);	
	}
	
	//상품 삭제
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="상품 번호", required=true, dataType="path", defaultValue="")
	})
	@ApiOperation(value="상품 삭제")
	@DeleteMapping(value="/delete/{no}")
	public JSONResult delete(@PathVariable(value="no") Long no) {
		//관리자 인증
		
		
		boolean flag = adminProductService.상품삭제(no);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//이미지  추가
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="이미지 번호", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="productNo", value="상품 번호", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="url", value="이미지 url", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="repOrBasic", value="이미지 구분", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="이미지 추가")
	@PostMapping(value="image/add")
	public ResponseEntity<JSONResult> imageAdd(@ModelAttribute @Valid ImageVO ivo,
						  	   		  		   BindingResult br) {
		
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
				
		
		//관리자 인증
		
		
		List<ImageVO> imageList = adminImageService.이미지추가(ivo);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("imageList", imageList);
		JSONResult result = JSONResult.success(data);
		//success >> 데이터 전송
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	//이미지 삭제
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="이미지 번호", required=true, dataType="path", defaultValue="")
	})
	@ApiOperation(value="이미지 삭제")
	@DeleteMapping(value="image/delete/{no}")
	public JSONResult imageDelete(@PathVariable(value="no") Long no) {
		//관리자 인증
		
		
		boolean flag = adminImageService.이미지삭제(no);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
	//옵션 추가
	@ApiImplicitParams({
		@ApiImplicitParam(name="productNo", value="상품 번호", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="name", value="옵션 이름", required=true, dataType="string", defaultValue=""),
		@ApiImplicitParam(name="depth", value="옵션 깊이", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="옵션 추가")
	@PostMapping(value="option/add")
	public ResponseEntity<JSONResult> OptionAdd(@ModelAttribute @Valid OptionVO fvo,
						  			 			BindingResult br) {
		
		//valid
		if(br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for(ObjectError error : errorList) {
				String msg = error.getDefaultMessage();
				JSONResult result = JSONResult.fail(msg);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);	
			}
		}
				
		
		//관리자 인증
		
		
		List<OptionVO> optionList = adminOptionService.옵션추가(fvo);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("optionList", optionList);
		JSONResult result = JSONResult.success(data);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	//옵션 삭제
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="옵션 번호", required=true, dataType="string", defaultValue="")
	})
	@ApiOperation(value="옵션 삭제")
	@DeleteMapping(value="option/delete/{no}")
	public JSONResult optionDelete(@PathVariable(value="no") Long no) {
		//관리자 인증
		
		
		boolean flag = adminOptionService.옵션삭제(no);
		
		//리턴 데이터
		Map<String, Object> data = new HashMap<>();
		data.put("flag", flag);
		JSONResult result = JSONResult.success(data);
		return result;
	}
	
}
