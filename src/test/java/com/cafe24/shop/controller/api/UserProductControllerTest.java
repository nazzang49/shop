package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.cafe24.shop.vo.MemberVO;
import com.google.gson.Gson;

//상품 컨트롤러 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class UserProductControllerTest {

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
	
	//(고객)상품목록 >> 추후 카테고리, 검색 활용한 조회 내용 추가
	@Test
	public void testAProductListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/product/list").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		//상품
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data['productList'][0].no", is(1)))
		.andExpect(jsonPath("$.data['productList'][0].name", is("반팔")))
		.andExpect(jsonPath("$.data['productList'][0].price", is(35000)))
		.andExpect(jsonPath("$.data['productList'][0].shortDescription", is("기능성 티셔츠")))
		.andExpect(jsonPath("$.data['productList'][0].alignUse", is("Y")))
		.andExpect(jsonPath("$.data['productList'][0].alignNo", is(1)))
		//썸네일 이미지
		.andExpect(jsonPath("$.data['imageList'][0].no", is(1)))
		.andExpect(jsonPath("$.data['imageList'][0].productNo", is(1)))
		.andExpect(jsonPath("$.data['imageList'][0].url", is("/image/shop-uploads/test.png")))
		.andExpect(jsonPath("$.data['imageList'][0].repOrBasic", is("R")));
	}
	
	//상품 상세
	@Test
	public void testBProductViewRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/product/detail/{no}",1L).contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		//상품
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
	
}
