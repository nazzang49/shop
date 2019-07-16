package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.config.AppConfig;
import com.cafe24.shop.config.TestWebConfig;

//(관리자) 상품 관리 컨트롤러 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
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
	
	//상품 목록 >> 비진열 포함
	@Test
	public void testAProductListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/adminproduct/list").contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.productList[0].no", is(1)))
		.andExpect(jsonPath("$.data.productList[0].name", is("productTest1")));
	}
	
	//상품 추가
	@Test
	public void testBProductWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/adminproduct/add")
						.param("name", "productTest1")
						.param("categoryNo", "1")
						.param("price", "20000")
						.param("shortDescription", "설명")
						.param("alignUse", "Y")
						.param("alignNo", "1")
						
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in name, price = 상품명 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(post("/api/adminproduct/add")
						.param("categoryNo", "1")
						.param("shortDescription", "설명")
						.param("alignUse", "Y")
						.param("alignNo", "1")
						
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//상품 수정
	@Test
	public void testCProductUpdate() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(put("/api/adminproduct/update/{no}",1L)
						.param("name", "productTest1-update")
						.param("shortDescription", "productTest1-update")
						.param("price", "300000")
						.param("alignUse", "Y")
						.param("alignNo", "3")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.productList[0].no", is(1)))
		.andExpect(jsonPath("$.data.productList[0].name", is("productTest1-update")))
		.andExpect(jsonPath("$.data.productList[0].price", is(300000)))
		.andExpect(jsonPath("$.data.productList[0].alignUse", is("Y")))
		.andExpect(jsonPath("$.data.productList[0].alignNo", is(3)));
		
		//invalidation in name, price = 상품명 empty, 가격 null 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(put("/api/adminproduct/update/{no}",1L)
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//상품 삭제
	@Test
	public void testDProductDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/adminproduct/delete/{no}",1L).contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//이미지 추가
	@Test
	public void testEImageWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/adminproduct/image/add")
						.param("productNo", "1")
						.param("url", "/image/shop-uploads/test.png")
						.param("repOrBasic", "R")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.imageList[2].productNo", is(1)))
		.andExpect(jsonPath("$.data.imageList[2].repOrBasic", is("R")));
		
		//invalidation in repOrBasic = 이미지구분 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(post("/api/adminproduct/image/add")
						.param("productNo", "1")
						.param("url", "/image/shop-uploads/test.png")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//이미지 삭제
	@Test
	public void testFImageDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/adminproduct/image/delete/{no}",1L).contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//옵션 추가
	@Test
	public void testGOptionWrite() throws Exception {
		//test >> api (상위)
		ResultActions resultActions = 
				mockMvc.perform(post("/api/adminproduct/option/add")
						.param("productNo", "1")
						.param("name", "베이지")
						.param("depth", "1")
						
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.optionList[2].productNo", is(1)))
		.andExpect(jsonPath("$.data.optionList[2].name", is("베이지")))
		.andExpect(jsonPath("$.data.optionList[2].depth", is(1)));
		
		//invalidation in productNo = 상품번호 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(post("/api/adminproduct/option/add")
						.param("name", "베이지")
						.param("depth", "1")
						
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	//옵션 삭제
	@Test
	public void testHOptionDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/adminproduct/option/delete/{no}",1L).contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}
