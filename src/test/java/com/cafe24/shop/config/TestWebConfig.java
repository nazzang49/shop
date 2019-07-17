package com.cafe24.shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.SwaggerConfig;
import com.cafe24.shop.config.TestMVCConfig;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shop.controller", "com.cafe24.shop.exception", "com.cafe24.shop.controller.api"})
@Import({TestMVCConfig.class, MessageConfig.class, SwaggerConfig.class})
public class TestWebConfig {
	
}
