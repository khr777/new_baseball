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
@RequestMapping(value="/baseball/play")
public class GameController {
	
	@Autowired
	private BallTestService ballTestServiceImpl;
	
	@RequestMapping(value="/game")
	public String main() {
		
		String htmlFolder = "view/game";
		String templatePath = htmlFolder;
		
		
		return templatePath;
	}
	
	@RequestMapping(value="/websocket_test")
	public String webocketTest(@PathVariable String action, HttpServletRequest request) {
		// return "view/main";
		return "websocket_test"; 
	}
	
}
