package com.worimodoo.baseball.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.worimodoo.interceptor.LoginInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**") // 인터셉터를 어떤 URL 패턴에 적용할지 설정
				.excludePathPatterns("/static/**")
				.excludePathPatterns("/baseball/member/login")
				.excludePathPatterns("/baseball/member/login-check")
				.excludePathPatterns("/baseball/member/join")
				.excludePathPatterns("/baseball/member/join-put")
				.excludePathPatterns("/baseball/game_rule")
				.excludePathPatterns("/baseball/main");
	}
}