package com.cafe24.config.app;

import java.util.ArrayList;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig2 {

	@Bean(name="springSecurityFilterChain")
	public FilterChainProxy filterChainProxy() {
		List<SecurityFilterChain> filterChains = new ArrayList<>();
		
		filterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/assets/**")));
		filterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/favicon.ico")));
		filterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"),
				//filter chains
				//1. 
				securityContextPersistenceFilter()
				
				//2. 
//				logoutFilter();
				
				//3.
//				usernamePasswordAuthenticationFilter();
				
				//4.
//				exceptionTranslationFilter();
				
				//5. ACL 등록
//				filterSecurityInterceptor()
				));
		

		
		return new FilterChainProxy(filterChains);
	}
	
	@Bean
	public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
		return new SecurityContextPersistenceFilter(new HttpSessionSecurityContextRepository());
	}
	
//	@Bean
//	public FilterSecurityInterceptor filterSecurityInterceptor() {
//		return new FilterSecurityInterceptor();
//	}
	
	//비밀번호 암호화 by BCrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
