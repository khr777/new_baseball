package site.canco.baseball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import site.canco.baseball.service.TestService;

@Controller
public class MainController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value="/")
	public String join() {	
		
		System.out.println("접속 성공 ");
		
		/*
		String contains = "join, login";
		if ( contains.contains(action) ) {
			path = "member/" + action;
		} else {
			path = "view/" + action;
		}
		*/
		return "view/make_time";	
	}

	
}
