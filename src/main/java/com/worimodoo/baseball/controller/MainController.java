package com.worimodoo.baseball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;
import com.worimodoo.baseball.dto.TestDto;
import com.worimodoo.baseball.service.BallTestService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="/baseball")
public class MainController {
	
	@Autowired
	private BallTestService ballTestServiceImpl;
	
	
	@RequestMapping(value="/{path}")
	public String main(@PathVariable("path") String viewPath) {
		
		String htmlFolder = "view/";
		String templatePath = htmlFolder + viewPath;
		
		TestDto testD = ballTestServiceImpl.getQueryTest();
		
		System.out.println("testMap Result : " + new Gson().toJson(testD));
		
		
		return templatePath;
	}
	
	
	@RequestMapping(value="/websocket_test")
	public String webocketTest(@PathVariable String action, HttpServletRequest request) {
		// return "view/main";
		return "websocket_test"; 
	}
	
}
