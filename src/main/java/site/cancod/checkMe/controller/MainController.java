package site.cancod.checkMe.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.cancod.checkMe.service.TimeService;
import site.cancod.checkMe.util.Util;
import site.cancod.checkMe.vo.Member;
import site.cancod.checkMe.vo.TimeName;

@Controller
public class MainController {
	
	@Autowired
	private TimeService timeService;

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
	
	@RequestMapping(value="/goMakeTime", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap goMakeTime(@RequestParam Map<String, Object> params, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		
		String timeName = params.get("timeName") + "";
		String userId = request.getAttribute("loginId") + "";
		
		int insertResult = timeService.insertTimeName(timeName, userId);
		
		
		
		result.put("result", "1");
		return result;
	}
	
	@RequestMapping(value="/getTimeNameList", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap getTimeNameList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		
		String userId = request.getAttribute("loginId") + "";
		
		System.out.println("userId : " + userId);
		
		List<TimeName> timeNameList = timeService.getAllTimeNameList(userId);
		
		
		
		
		result.put("result", "1");
		return result;
	}
	
}
