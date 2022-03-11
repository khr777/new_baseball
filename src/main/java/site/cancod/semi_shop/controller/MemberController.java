package site.cancod.semi_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping(value="/join")
	public String join() {	
		
		return "member/join";
	}
	
	@RequestMapping(value="/login")
	public String login() {	
		
		return "member/login";
	}
	
	
}
