package com.cafe24.shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({DBConfig.class, MyBatisConfig.class})
@ComponentScan({"com.cafe24.mysite.repository", "com.cafe24.mysite.service", "com.cafe24.mysite.aspect", "com.cafe24.fileupload.service"})
public class AppConfig {

	
	
}
