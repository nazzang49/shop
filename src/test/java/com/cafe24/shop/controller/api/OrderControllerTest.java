package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

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
import com.google.gson.Gson;

//주문 컨트롤러 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class OrderControllerTest {

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
	
	//(고객) 장바구니 담기
	@Test
	public void testCartAdd() throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberId","test");
		map.put("productNo",1L);
		map.put("secondOptionNo",1L);
		//개당 2만원
		map.put("cartAmount",3L);
		map.put("cartPrice",60000);
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/order/cart/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	//(고객) 장바구니 조회
	@Test
	public void testCartList() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/order/cart/{id}","test").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].memberId", is("test")))
		.andExpect(jsonPath("$.data[0].productNo", is(1)))
		.andExpect(jsonPath("$.data[0].secondOptionNo", is(1)))
		.andExpect(jsonPath("$.data[0].cartAmount", is(3)))
		.andExpect(jsonPath("$.data[0].cartPrice", is(60000)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
	
	
}
