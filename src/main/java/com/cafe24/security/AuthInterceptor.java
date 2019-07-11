package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.shop.vo.MemberVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
						     HttpServletResponse response,
						     Object handler)
						     throws Exception {
		
		if(handler instanceof HandlerMethod==false) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		if(auth==null) {
			return true;
		}
		
		HttpSession session = request.getSession();
		
		if(session==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		MemberVO vo = (MemberVO)session.getAttribute("authUser");
		if(vo==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		Auth.Role role = auth.role();
		
		if(role==Auth.Role.USER) {
			return true;
		}
		
		return true;
	}
}
