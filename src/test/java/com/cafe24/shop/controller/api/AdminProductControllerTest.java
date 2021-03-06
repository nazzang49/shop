package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.config.AppConfig;
import com.cafe24.shop.config.TestWebConfig;
import com.cafe24.shop.vo.ImageVO;
import com.cafe24.shop.vo.OptionVO;
import com.cafe24.shop.vo.ProductVO;
import com.google.gson.Gson;

//(관리자) 상품 관리 컨트롤러 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
@Rollback(true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminProductControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void setDB() {
		
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	//상품 목록
	@Test
	public void testBProductListRead() throws Exception {
		//카테고리 >> 진열번호 desc
		ResultActions resultActions = 
				mockMvc.perform(get("/api/admin/product/list/{categoryNo}",1L).contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.productList[0].no", is(2)))
		.andExpect(jsonPath("$.data.productList[0].name", is("product1")));
		
		//전체 >> 상품번호 desc
		resultActions = 
				mockMvc.perform(get("/api/admin/product/list").contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.productList[0].no", is(4)))
		.andExpect(jsonPath("$.data.productList[1].no", is(2)));
	}
	
	//상품 추가 >> 기본 정보 + 이미지 + 옵션
	@Test
	public void testAProductWrite() throws Exception {
		
		ProductVO productVO = new ProductVO();
		productVO.setName("product1");
		productVO.setCategoryNo(1L);
		productVO.setPrice(20000L);
		productVO.setShortDescription("product1-des");
		productVO.setAlignUse("Y");
		
		List<ImageVO> imageList = new ArrayList<>();
		imageList.add(new ImageVO(1L, "image1", "R"));
		imageList.add(new ImageVO(1L, "image2", "B"));
		
		productVO.setImageList(imageList);
		
		List<OptionVO> optionList = new ArrayList<>();
		optionList.add(new OptionVO(1L, "black", 1L));
		optionList.add(new OptionVO(1L, "M", 2L));
		
		productVO.setOptionList(optionList);
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/admin/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVO)));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in name = 상품명 입력값 실패 케이스
		productVO.setName(null);
		
		resultActions = 
				mockMvc.perform(post("/api/admin/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVO)));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
		
		//invalidation in price = 상품가격 입력값 실패 케이스 
		productVO.setPrice(null);
		
		resultActions = 
				mockMvc.perform(post("/api/admin/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVO)));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//상품 수정
	@Test
	public void testCProductUpdate() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(put("/api/admin/product/update/{no}",1L)
						.param("name", "product1-update")
						.param("categoryNo", "2")
						.param("shortDescription", "설명-update")
						.param("price", "30000")
						.param("alignUse", "Y")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in name = 상품명 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(put("/api/admin/product/update/{no}",1L)
						.param("categoryNo", "2")
						.param("shortDescription", "설명-update")
						.param("price", "30000")
						.param("alignUse", "N")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
		
		//invalidation in price = 상품가격 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(put("/api/admin/product/update/{no}",1L)
						.param("name", "product1-update")
						.param("categoryNo", "2")
						.param("shortDescription", "설명-update")
						.param("alignUse", "N")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//상품 삭제 >> cascade 완료
	@Test
	public void testDProductDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/admin/product/delete/{no}",2L)
						.param("categoryNo", "1")
						.param("alignNo", "1")
						.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//이미지 추가 >> 리스트
	@Test
	public void testEImageWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/admin/product/{productNo}/image/add", 4L)
						.param("url", "image1")
						.param("url", "image2")
						.param("url", "image3")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in repOrBasic = 이미지구분 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(post("/api/admin/product/{productNo}/image/add", 4L)
						.param("url", "image1")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail"))) ;
	}
	
	//이미지 목록
	@Test
	public void testIImageListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/admin/product/{productNo}/image/list", 4L).contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.imageList[0].url", is("image3")))
		.andExpect(jsonPath("$.data.imageList[1].url", is("image2")))
		.andExpect(jsonPath("$.data.imageList[2].url", is("image1")));
	}
	
	//이미지 삭제
	@Test
	public void testFImageDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/admin/product/image/delete",9L)
						.param("no", "11")
						.param("no", "12")
						.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//옵션 목록
	@Test
	public void testJOptionListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/admin/product/{productNo}/option/list", 10L).contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.optionList[0].name", is("M")))
		.andExpect(jsonPath("$.data.optionList[1].name", is("black")));
	}
	
	//옵션 추가
	@Test
	public void testGOptionWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/admin/product/{productNo}/option/add", 11L)
						.param("name", "pink")
						.param("depth", "1")
						.param("name", "275")
						.param("depth", "2")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
	}
	
	//옵션 삭제
	@Test
	public void testHOptionDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/admin/product/option/delete")
						.param("no", "7")
						.param("no", "8")
						.param("no", "9")
						.param("no", "10")
						.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	
	/*
	 * 상품옵션 >> 상품 상세 페이지에 노출될 실제 각 상품들의 옵션 및 재고 정보 
	 */
	
	
	
	//상품옵션 추가
	@Test
	public void testKProductOptionWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/admin/product/{productNo}/productOption/add", 1L)
						.param("firstOptionNo", "1")
						.param("firstOptionNo", "2")
						.param("secondOptionNo", "3")
						.param("secondOptionNo", "4")
						.param("remainAmount", "1000")
						.param("remainAmount", "2000")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
	}
	
	//상품옵션 삭제
	@Test
	public void testLProductOptionDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/admin/product/productOption/delete")
						.param("no", "1")
						.param("no", "2")
						.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}
