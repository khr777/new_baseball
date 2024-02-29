package com.worimodoo.baseball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.worimodoo.baseball.dto.Member;
import com.worimodoo.baseball.service.MemberService;
import com.worimodoo.baseball.util.PathCodeInterface;
import com.worimodoo.baseball.util.ResultMessageInterface;
import com.worimodoo.baseball.util.ReturnCodeInterface;
import com.worimodoo.baseball.util.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/baseball/member")
public class MemberController implements ReturnCodeInterface, ResultMessageInterface, PathCodeInterface {

	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String main() {
		String htmlFolder = "view/join";
		String templatePath = htmlFolder;
		
		
		return templatePath;
	}
	
	
	@RequestMapping(value="/login")
	public String login() {
		String htmlFolder = "view/login";
		String templatePath = htmlFolder;
		
		
		return templatePath;
	}
	
	
	
	
	
	/**
	 * 로그인 부분 소스 정리 
	 */
	
	/**
	 * [로그인]  
	 * @param member 가입 정보 
	 * @return
	 * 
	 * Ajax 통신으로 객체를 Member로 변환 받습니다. -> @RequestBody 
	 * Ajax로 반환할 데이터를 Member에 담아 리턴하면 Ajax 호출 성공 후 활용할 수 있습니다. -> @RequestBody
	 */
	@RequestMapping(value="/login-check", method = RequestMethod.POST)
	@ResponseBody
	public Member memberLoginCheck(HttpServletRequest request, @RequestBody Member member, Model model) {
		System.out.println("member : " + new Gson().toJson(member));
		
		member = memberService.loginInfoCheck(member);
		
		if ( member.getResult().equals(RETURN_SUCCESS) ) {
			HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 60);
		}
		
		return member;
		
	}
	
	
	
	/**
	 * [로그아웃]  
	 * @param member  
	 * @return
	 * 
	 */
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	@ResponseBody
	public Member memberLogout(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		System.out.println("...? : " + session.getAttribute("loginMember"));
		System.out.println("...? : " + new Gson().toJson(session.getAttribute("loginMember")));
		
		Member member = (Member) session.getAttribute("loginMember");
			
			
		if ( !Utils.isEmptyOrNull("1", member.getNickName(), null) ) {
			session.removeAttribute("loginMember");
			member.setResult(RETURN_SUCCESS); 
			member.setResultMsg(member.getNickName() + " 님, 좋은 하루 보내세요 :)");
			member.setView(PATH_MAIN);
		} else {
			member = new Member(); 
			member.setResult(RETURN_ERROR); 
			member.setResultMsg("지속적으로 로그아웃이 불가한 경우, 관리자에게 문의해 주세요.");
			member.setView(PATH_MAIN);
		}
		
		return member;
		
	}
	
	
	
	
	
	/**
	 * [회원가입]  
	 * @param member 가입 정보 
	 * @return
	 * 
	 * Ajax 통신으로 객체를 Member로 변환 받습니다. -> @RequestBody 
	 * Ajax로 반환할 데이터를 Member에 담아 리턴하면 Ajax 호출 성공 후 활용할 수 있습니다. -> @RequestBody
	 */
	@RequestMapping(value="/join-put", method = RequestMethod.POST)
	@ResponseBody
	public Member memberMain(@RequestBody Member member, Model model) {
		
		String joinValidResult = member.getJoinValidResult();
		
		if ( joinValidResult.equals(RETURN_EMPTY_OR_NULL) ) {
			
			member.setResult(RETURN_ERROR);
			member.setResultMsg("회원 가입 정보가 올바르지 않습니다. 회원 가입을 다시 진행해 주세요.");
			member.setView(PATH_JOIN); 
			return member;
		}
		
		
		int overlapCount = memberService.getNickNameOverlapCount(member.getNickName().trim());
		if ( overlapCount > 0 ) {
			
			member.setResult(RETURN_OVERLAP);
			member.setResultMsg("이미 존재하는 닉네임 입니다.\n다시 입력해 주세요.");
			member.setView(PATH_JOIN); 
			
			return member;
		}
		
		
		int joinResult = memberService.joinMember(member);
		String result = "";  
		String resultMsg = ""; 
		String view = ""; 
		if ( joinResult > -1 ) {
			result = RETURN_SUCCESS; 
			resultMsg = "게임을 시작해 보세요!"; 
			view = PATH_MAIN;
		} else {
			result = RETURN_ERROR;
			resultMsg = "계정 생성 중에 문제가 발생했습니다.\n관리자에게 문의해 주세요.";
		}
		
		member.setResult(result);
		member.setResultMsg(resultMsg);
		member.setView(view);
		
		return member;
		
	}
	
	
	@RequestMapping(value="/websocket_test")
	public String webocketTest(@PathVariable String action, HttpServletRequest request) {
		// return "view/main";
		return "websocket_test"; 
	}
	
}
