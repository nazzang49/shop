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
import com.cafe24.shop.vo.CartVO;
import com.google.gson.Gson;

//주문 컨트롤러 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class UserOrderControllerTest {

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
	
	//(회원) 장바구니 추가
	@Test
	public void testACartWrite() throws Exception {
		CartVO cartVO = new CartVO();
		
		cartVO.setMemberId("test");
		cartVO.setProductOptionNo(1L);
		cartVO.setCartAmount(3L);
		cartVO.setCartPrice(60000L);
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/order/cart/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(cartVO)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));

		//invalidation in cartAmount = 수량 입력값 실패 케이스
		cartVO = new CartVO();
		cartVO.setMemberId("test");
		cartVO.setProductOptionNo(1L);
		cartVO.setCartPrice(60000L);
		
		//test >> api
		resultActions = 
				mockMvc.perform(post("/api/order/cart/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(cartVO)));
		
		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//(회원) 장바구니 조회
	@Test
	public void testBCartListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/order/cart/{memberId}","test").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.categoryList[0]", is("test")));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}
