package site.cancod.semi_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value="/{action}")
	public String join(@PathVariable String action) {	
		String path = "view/" + action;
		/*
		String contains = "join, login";
		if ( contains.contains(action) ) {
			path = "member/" + action;
		} else {
			path = "view/" + action;
		}
		*/
		return path;	
	}
}
