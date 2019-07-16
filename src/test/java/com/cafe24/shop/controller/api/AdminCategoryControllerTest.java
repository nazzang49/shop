package com.cafe24.shop.controller.api;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

//(관리자) 카테고리 관리 컨트롤러 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminCategoryControllerTest {

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
	
	//카테고리 목록
	@Test
	public void testBCategoryListRead() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(get("/api/admincategory/list").contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.categoryList[0].no", is(1)))
		.andExpect(jsonPath("$.data.categoryList[0].name", is("categoryTest1")));

	}
	
	//카테고리 추가
	@Test
	public void testACategoryWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/admincategory/add")
						.param("name", "categoryTest1")
						.param("groupNo", "1")
						.param("depth", "1")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in name = 이름 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(post("/api/admincategory/add")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//카테고리 수정
	@Test
	public void testCCategoryUpdate() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(put("/api/admincategory/update/{no}",1L)
						.param("name", "categoryTest2")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
		
		//invalidation in name = 이름 입력값 실패 케이스
		resultActions = 
				mockMvc.perform(put("/api/admincategory/update/{no}",1L)
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest()).andDo(print())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	//카테고리 삭제
	@Test
	public void testDCategoryDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/admincategory/delete/{no}",1L).contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}
