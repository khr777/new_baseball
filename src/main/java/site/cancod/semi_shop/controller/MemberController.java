package site.cancod.semi_shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import site.cancod.checkMe.util.Util;
import site.cancod.semi_shop.service.MemberService;
import site.cancod.semi_shop.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/join")
	public String join() {	
		
		return "member/join";
	}
	
	@RequestMapping(value="/insertJoin", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap insertJoin(@RequestParam Map<String, Object> params) {	
		
		ModelMap result = new ModelMap();
		
		String loginId = params.get("loginId") + "";
		String loginPw = (String)params.get("loginPw");
		String name = params.get("name") + "";
		String email = params.get("email") + "";
		
		String shaPw = Util.getSha256(loginPw);
		
		if ( loginId == null || loginPw == null || name == null || email == null ) {
			result.put("result", "0");
			return result;
		}
		
		int checkLoginId = memberService.getCheckLoginId(loginId);
		if ( checkLoginId > 0 ) {
			result.put("result", "overlap");
			return result;
		}
				
		int insertResult = memberService.insertJoin(loginId, shaPw, name, email);
		
		if ( insertResult > -1 ) {
			result.put("result","1");
		} else {
			result.put("result", "0");
		}
		
		return result;
	}
	
	@RequestMapping(value="/login")
	public String login() {	
		
		return "member/login";
	}
	
	@RequestMapping(value="/goLogin", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap goLogin(@RequestParam Map<String, Object> params, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		
		String loginId = params.get("loginId") + "";
		String loginPw = params.get("loginPw") + "";
		
		String realPw = Util.getSha256(loginPw);
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( !realPw.equals(member.getLoginPw()) ) {
			result.put("result", "0");
			return result;
		}
		
		// 로그인 성공 처리
	    HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
	    session.setAttribute("loginId", member.getSeq()); 
		
		result.put("result", "1");
		return result;
	}
	
}
