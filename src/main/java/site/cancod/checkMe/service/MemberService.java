package site.cancod.checkMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.cancod.checkMe.dao.MemberDao;
import site.cancod.checkMe.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	public int insertJoin(String loginId, String loginPw, String name, String email) {
		return memberDao.insertJoin(loginId, loginPw, name, email);
	}

	public int getCheckLoginId(String loginId) {
		return memberDao.getCheckLoginId(loginId);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	
}
