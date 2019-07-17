package com.cafe24.shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;
import com.cafe24.config.app.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({DBConfig.class, MyBatisConfig.class, SecurityConfig.class})
@ComponentScan({"com.cafe24.shop.repository", "com.cafe24.shop.service"})
public class AppConfig {
	
}
