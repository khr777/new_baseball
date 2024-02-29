package com.worimodoo.baseball.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.worimodoo.baseball.dto.GameDto;
import com.worimodoo.baseball.dto.Member;
import com.worimodoo.baseball.dto.TestDto;
import com.worimodoo.baseball.service.BallTestService;
import com.worimodoo.baseball.service.GameService;
import com.worimodoo.baseball.service.MemberService;
import com.worimodoo.baseball.util.CodeInterface;
import com.worimodoo.baseball.util.PathCodeInterface;
import com.worimodoo.baseball.util.ReturnCodeInterface;
import com.worimodoo.baseball.util.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/baseball/play")
public class GameController implements ReturnCodeInterface, PathCodeInterface, CodeInterface {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value="/game")
	public String main() {
		
		String htmlFolder = "view/game";
		String templatePath = htmlFolder;
		
		
		return templatePath;
	}

	@RequestMapping(value="/start-game", method = RequestMethod.POST)
	@ResponseBody
	public GameDto startGame(HttpServletRequest request, @RequestBody GameDto gameDto, Model model) {
		
		
		HttpSession session = request.getSession();
		
		if ( session.getAttribute(PLAY_BALL) != null ) {
			session.removeAttribute(PLAY_BALL);
		}
		
		Member member = (Member) session.getAttribute("loginMember");
		String playball = gameDto.getPlayball().replace("[", "").replace("]", "");
		
		session.setAttribute(PLAY_BALL, playball);
		
		gameDto.setId(member.getId());
		gameDto.setNickName(member.getNickName());
		gameDto.setPlayball(playball);
		
		session.setAttribute(PLAY_BALL_DTO, gameDto);
		
		int insertResult = gameService.insertStartGameData(gameDto);
		
		if ( insertResult > -1 ) {
			gameDto.setResult(RETURN_SUCCESS); 
		} else {
			gameDto.setResult(RETURN_ERROR); 
			gameDto.setResultMsg("일시적인 오류로 처음으로 돌아갑니다.<br>지속적으로 플레이가 불가능할 경우, 관리자에게 문의해 주세요.");
			gameDto.setView(PATH_MAIN);
		}
		
		return gameDto;
		
	}
	
	@RequestMapping(value="/check-result", method = RequestMethod.POST)
	@ResponseBody
	public GameDto checkResult(HttpServletRequest request, @RequestBody List<Object> list, Model model) {
		
		
		HttpSession session = request.getSession();
		
		GameDto gameDto = (GameDto) session.getAttribute(PLAY_BALL_DTO);
		String playball = String.valueOf(list).replace("[", "").replace("]", "").replaceAll(" ", "");
		
		if ( gameDto.getPlayball().equals(playball) ) {
			gameDto.setResult(RETURN_SUCCESS);
			gameDto.setResultMsg("홈런!<br>모든 숫자를 맞추셨군요!");
		} else {
			gameDto.setResult(RETURN_ERROR);
		}
		
		System.out.println("gameDto.getPlayball : " + gameDto.getPlayball());
		System.out.println("playball : " + playball);
		
		return gameDto;
		
	}
	
	
	@RequestMapping(value="/websocket_test")
	public String webocketTest(@PathVariable String action, HttpServletRequest request) {
		// return "view/main";
		return "websocket_test"; 
	}
	
}
