package com.worimodoo.baseball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worimodoo.baseball.dao.MemberDao;
import com.worimodoo.baseball.dto.Member;
import com.worimodoo.baseball.dto.TestDto;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public int joinMember(Member member) {
		return memberDao.joinMember(member);
	}

	public int getNickNameOverlapCount(String nickName) {
		return memberDao.getNickNameOverlapCount(nickName);
	} 
}
