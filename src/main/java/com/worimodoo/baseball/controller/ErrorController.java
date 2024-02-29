package com.worimodoo.baseball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/error")
public class ErrorController {
	
	public String error() {
		
		return "view/error";
	}
	
}
