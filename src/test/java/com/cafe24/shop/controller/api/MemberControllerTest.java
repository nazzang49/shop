package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.cafe24.shop.vo.MemberVO;
import com.google.gson.Gson;

//사용자 컨트롤러 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberControllerTest {

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
	
	//조인 >> 아이디 중복 체크
	@Test
	public void testMemberCheckId() throws Exception {
		Map<String, String> map = new HashMap<>();
		
		map.put("id","test");
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/member/checkid").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//조인
	@Test
	public void testMemberJoin() throws Exception {
		MemberVO mvo = new MemberVO();
		
		mvo.setId("test");
		mvo.setName("test");
		mvo.setPassword("pw");
		mvo.setAuth("USER");
		mvo.setPhone("010-1111-1111");
		mvo.setEmail("test@gmail.com");
		mvo.setAddress("서울");
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/member/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(mvo)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//로그인
	@Test
	public void testMemberLogin() throws Exception {
		Map<String, String> map = new HashMap<>();
		
		map.put("id","test");
		map.put("password","pw");
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//회원조회
	@Test
	public void testMemberRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/member/{id}","test").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.mvo.id", is("test")))
		.andExpect(jsonPath("$.data.mvo.password", is("pw")))
		.andExpect(jsonPath("$.data.mvo.name", is("test")))
		.andExpect(jsonPath("$.data.mvo.address", is("서울")))
		.andExpect(jsonPath("$.data.mvo.phone", is("010-1111-1111")))
		.andExpect(jsonPath("$.data.mvo.email", is("test@naver.com")))
		.andExpect(jsonPath("$.data.mvo.auth", is("USER")))
		.andExpect(jsonPath("$.data.mvo.regDate", is("2018-10-01")));
	}
	
	//회원수정
	@Test
	public void testMemberUpdate() throws Exception {
		MemberVO mvo = new MemberVO();
		
		mvo.setName("test");
		mvo.setPassword("pw");
		mvo.setAuth("USER");
		mvo.setPhone("010-1111-1111");
		mvo.setEmail("test@gmail.com");
		mvo.setAddress("서울");
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(put("/api/member/update/{id}","test").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(mvo)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
		
	//회원탈퇴
	@Test
	public void testMemberDelete() throws Exception {
		Map<String, String> map = new HashMap<>();
		
		map.put("password","pw");
		
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/member/delete/{id}","test").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}
