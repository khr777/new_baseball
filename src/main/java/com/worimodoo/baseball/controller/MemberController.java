package com.worimodoo.baseball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.worimodoo.baseball.dto.Member;
import com.worimodoo.baseball.service.MemberService;
import com.worimodoo.baseball.util.PathCodeInterface;
import com.worimodoo.baseball.util.ReturnCodeInterface;
import com.worimodoo.baseball.util.ResultMessageInterface;

import jakarta.servlet.http.HttpServletRequest;


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
			
			model.addAttribute(RESULT, RETURN_ERROR); 
			model.addAttribute(RESULT_MSG, "회원 가입 정보가 올바르지 않습니다. 회원 가입을 다시 진행해 주세요.");
			
			member.setResult(RETURN_ERROR);
			member.setResultMsg("회원 가입 정보가 올바르지 않습니다. 회원 가입을 다시 진행해 주세요.");
			member.setView(VIEW_JOIN); 
			return member;
		}
		
		int overlapCount = memberService.getNickNameOverlapCount(member.getNickName().trim());
		if ( overlapCount > 0 ) {
			model.addAttribute(RESULT, RETURN_OVERLAP); 
			model.addAttribute(RESULT_MSG, "이미 존재하는 닉네임 입니다.\n다시 입력해 주세요.");
			
			member.setResult(RETURN_OVERLAP);
			member.setResultMsg("이미 존재하는 닉네임 입니다.\n다시 입력해 주세요.");
			member.setView(VIEW_JOIN); 
			
			return member;
		}
		
		
		int joinResult = memberService.joinMember(member);
		String result = "";  
		String resultMsg = ""; 
		String view = ""; 
		if ( joinResult > -1 ) {
			result = RETURN_SUCCESS; 
			resultMsg = "게임을 시작해 보세요!"; 
			view = VIEW_MAIN;
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
