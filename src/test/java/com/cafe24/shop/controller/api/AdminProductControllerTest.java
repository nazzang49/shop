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
	
	//상품 목록 >> 비진열 상품 포함
	@Test
	public void test_A_ProductListRead() throws Exception {
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
	public void test_B_ProductWrite() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(post("/api/adminproduct/add")
						.param("name", "productTest1")
						.param("price", "20000")
						.param("shortDescription", "설명")
						.param("alignUse", "Y")
						.param("alignNo", "1")
						.param("categoryNo", "1")
						
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//상품 수정 >> 상품명, 상품가격
	@Test
	public void test_C_ProductUpdate() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(put("/api/adminproduct/update/{no}",1L)
						.param("name", "productTest1-update")
						.param("price", "250000")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.productList[0].no", is(1)))
		.andExpect(jsonPath("$.data.productList[0].name", is("productTest1-update")))
		.andExpect(jsonPath("$.data.productList[0].price", is(250000)));
	}
	
	//상품 삭제
	@Test
	public void test_D_ProductDelete() throws Exception {
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
	public void test_E_ProductImageWrite() throws Exception {
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
	}
	
	//상품 삭제
	@Test
	public void test_E_ProductImageDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/adminproduct/image/delete/{no}",1L).contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	//상위 옵션 추가 
	@Test
	public void test_F_ProductFirstOptionWrite() throws Exception {
		//test >> api (상위)
		ResultActions resultActions = 
				mockMvc.perform(post("/api/adminproduct/firstoption/add")
						.param("no", "3")
						.param("productNo", "1")
						.param("name", "패키지 상품")
						
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.firstOptionList[2].no", is(3)))
		.andExpect(jsonPath("$.data.firstOptionList[2].productNo", is(1)))
		.andExpect(jsonPath("$.data.firstOptionList[2].name", is("패키지 상품")));
		
	}
	
	//상위 옵션 삭제
	//추후, 개별 삭제 추가
	@Test
	public void test_G_ProductFirstOptionDelete() throws Exception {
		//test >> api
		ResultActions resultActions = 
				mockMvc.perform(delete("/api/adminproduct/firstoption/delete/{no}",1L).contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.flag", is(true)));
	}
	
	@AfterClass
	public static void resetDB() {
		
	}
	
}
