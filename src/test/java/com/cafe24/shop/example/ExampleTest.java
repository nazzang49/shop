package com.cafe24.shop.example;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

//import com.cafe24.mysite.config.AppConfig;
//import com.cafe24.mysite.config.TestWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class ExampleTest {

	// 테스트 메소드 간 공유해야 할 변수 있다면 static으로 미리 선언
	private static StringBuilder output = new StringBuilder();
	
	@BeforeClass
	public static void setUp() {
		System.out.println("비포");
	}
	
	@AfterClass
	public static void tearDown() {
		System.out.println("애프터" + output.toString());
	}
	
	@Test
	public void myBTest() {
		System.out.println("B");
		output.append("b");
	}
	
	@Test
	public void myCTest() {
		System.out.println("C");
		output.append("c");
	}
	
	@Test
	public void testAssert() {
		assertTrue(true);
		assertNotNull(new Object());
		assertEquals(1,1);
		assertEquals(new String("3"),3);
		assertSame("hello", "hello");
	}
	
}
