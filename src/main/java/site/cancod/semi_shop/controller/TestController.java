package site.cancod.semi_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import site.cancod.semi_shop.service.TestService;
import site.cancod.semi_shop.vo.Article;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value="/")
	public String test() {	
		
		Article article = testService.getData();
		
		Gson gson = new Gson();
		
		return gson.toJson(article);
	}
}
