package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

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

//(관리자) 회원 관리 컨트롤러 테스트
//사용자 컨트롤러 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminUserControllerTest {

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
	
	//회원목록
	@Test
	public void testAUserListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/adminuser/list").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		//상품
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data['userList'][0].id", is("test1")))
		.andExpect(jsonPath("$.data['userList'][0].name", is("박진영")))
		.andExpect(jsonPath("$.data['userList'][0].address", is("서울")));
	}
	
	//회원 삭제
	@Test
	public void testBUserDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/adminuser/delete/{id}","test1").contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	
}
