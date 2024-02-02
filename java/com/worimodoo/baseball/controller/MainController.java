package com.worimodoo.baseball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MainController {
	
	
	// 메인 
	@RequestMapping(value="/")
	public String main() {
		// return "view/main";
		return "main"; 
	}
	
	@RequestMapping(value="/test_main")
	public String mainTest() {
		
		return "test_view/test"; 
	}
	
	@RequestMapping(value="/index")
	public String index() {
		// return "view/main";
		return "index"; 
	}
	
	@RequestMapping(value="/game/{serviceType}/{action}")
	public String goAction(@PathVariable String serviceType, @PathVariable String action, HttpServletRequest request) {
		
		if ( serviceType.equals("join") ) {
			if ( action.equals("joinSubmit")) {
				
			} else if ( action.equals("join_start") ) {
				action = "join"; 
			}
		} else {
			
		}
		
		return action; 
	}
	
	@RequestMapping(value="/websocket_test")
	public String webocketTest(@PathVariable String action, HttpServletRequest request) {
		// return "view/main";
		return "websocket_test"; 
	}
	
}
