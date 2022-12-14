package site.cancod.checkMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import site.cancod.checkMe.service.TestService;
import site.cancod.checkMe.vo.Article;

// @RestController
@Controller
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value="/")
	public String test() {	
		
		Article article = testService.getData();
		
		Gson gson = new Gson();
		
		return gson.toJson(article);
	}
	
	@RequestMapping(value="/test")
	public String test2() {
		return "view/time_check";
	}
}
