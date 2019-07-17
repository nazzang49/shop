package com.cafe24.config.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * spring security filter chain >> 자동 11개 생성
 * 
 *   1. ChannelProcessingFilter
	 2. SecurityContextPersistenceFilter		( auto-config default / 필수)
	 3. ConcurrentSessionFilter
	 4. LogoutFilter							( auto-config default / 필수)
	 5. UsernamePasswordAuthenticationFilter	( auto-config default / 필수)
	 6. DefaultLoginPageGeneratingFilter		( auto-config default )
	 7. CasAuthenticationFilter
	 8. BasicAuthenticationFilter				( auto-config default / 필수)
	 9. RequestCacheAwareFilter					( auto-config default )
	10. SecurityContextHolderAwareRequestFilter	( auto-config default )
	11. JaasApiIntegrationFilter
	12. RememberMeAuthenticationFilter
	13. AnonymousAuthenticationFilter			( auto-config default )
	14. SessionManagementFilter					( auto-config default )
	15. ExceptionTranslationFilter				( auto-config default / 필수)
	16. FilterSecurityInterceptor				( auto-config default / 필수)
 * 
 */

//spring security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//spring security filter 연결 작업
	//WebSecurity 객체 >> springSecurityFilterChain을 이름으로 가지는 DelegatingFilterProxy 빈 생성
	//springSecurityFilterChain은 위 주석 처리 된 filter들 위임
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(web);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
	}
	
}
