package com.worimodoo.baseball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worimodoo.baseball.dao.MemberDao;
import com.worimodoo.baseball.dto.Member;
import com.worimodoo.baseball.dto.TestDto;
import com.worimodoo.baseball.util.PathCodeInterface;
import com.worimodoo.baseball.util.ReturnCodeInterface;
import com.worimodoo.baseball.util.ReturnMessageInterface;

@Service
public class MemberService implements ReturnCodeInterface, PathCodeInterface {
	
	@Autowired
	private MemberDao memberDao;
	
	public int joinMember(Member member) {
		return memberDao.joinMember(member);
	}

	public int getNickNameOverlapCount(String nickName) {
		return memberDao.getNickNameOverlapCount(nickName);
	}

	public Member loginInfoCheck(Member member) {
		
		int validNickNameCheck = getNickNameOverlapCount(member.getNickName()); 
		
		if ( validNickNameCheck == 0 ) {
			member.setResult(RETURN_EMPTY_OR_NULL);
			member.setResultMsg("존재하지 않는 닉네임 입니다.");
			return member;
		}
		
		Member returnMember = memberDao.loginInfoCheck(member); 
		
		if ( returnMember != null ) {
			returnMember.setResultMsg("베이스볼 게임을 시작할 준비가 되었습니다!");
			returnMember.setResult(RETURN_SUCCESS);
			returnMember.setView(PATH_MAIN);
		} else {
			returnMember = new Member();
			returnMember.setResult(RETURN_EMPTY_OR_NULL);
			returnMember.setResultMsg("패스워드를 다시 입력해 주세요.");
		}
		
		return returnMember; 
	} 
}
