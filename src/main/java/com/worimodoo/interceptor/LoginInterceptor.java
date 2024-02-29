package com.worimodoo.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.worimodoo.baseball.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		log.debug("==================== START ====================");
//		log.debug(" Request URI \t: " + request.getRequestURI());
		log.info("==================== START ====================");
		log.info(" Request URI \t: " + request.getRequestURI());
		
		
		boolean returnInterceptor = true;
		
		
		// 1. 세션에서 회원 정보 조회
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");
		
		if ( member != null ) {
			System.out.println("member nickName : " + member.getNickName());
			
		} else {
			log.info("미인증 사용자 요청");
			returnInterceptor = false;
			response.sendRedirect("member/login");
			System.out.println("member Null");
		}
		
		return returnInterceptor;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("==================== END ====================");
	}

}