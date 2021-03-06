package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

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
import com.cafe24.shop.vo.CartVO;
import com.google.gson.Gson;

//(회원) 주문 컨트롤러 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
//@Transactional
//@Rollback(true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	
	//장바구니 추가
//	@Test
	public void testACartWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/order/cart/add")
						.param("memberId", "user1")
						.param("productOptionNo", "4")
						.param("cartAmount", "4")
						.param("productPrice", "20000")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));

		//invalidation in cartAmount = 수량 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(post("/api/order/cart/add")
						.param("memberId", "user1")
						.param("productOptionNo", "3")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//장바구니 목록
//	@Test
	public void testBCartListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/order/cart/{memberId}","user1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.cartList[0].productOptionNo", is(3)))
		.andExpect(jsonPath("$.data.cartList[1].productOptionNo", is(4)));
	}
	
	//장바구니 수량, 상품옵션 수정
//	@Test
	public void testCCartUpdate() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(put("/api/order/cart/update/{no}",2L)
						.param("productOptionNo", "4")
						.param("cartAmount", "2")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in cartAmount = 수량 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(put("/api/order/cart/update/{no}",1L)
						.param("productOptionNo", "3")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	//장바구니 삭제
//	@Test
	public void testDCartDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/order/cart/delete")
						.param("no", "2")
						.param("no", "3")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//주문 추가 >> 주문 상세 포함
	@Test
	public void testFOrderByCartWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/order/add")
						.param("memberId", "user1")
						.param("customerName", "박진영")
						.param("customerAddress", "미국")
						.param("customerPhone", "010-1111-1111")
						.param("customerEmail", "user1@naver.com")
						.param("receiverName", "박우성")
						.param("receiverAddress", "한국")
						.param("receiverPhone", "010-2222-2222")
						.param("receiverMsg", "부재 시 경비실")
						.param("paymentCategory", "계좌이체")
						.param("paymentPrice", "140000")
						//장바구니 번호
						.param("cartNoList", "4")
						.param("cartNoList", "5")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//주문 상세
//	@Test
	public void testGOrderListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/order/list/{memberId}","test").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}

