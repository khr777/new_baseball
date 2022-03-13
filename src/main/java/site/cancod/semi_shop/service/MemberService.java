package site.cancod.semi_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.cancod.semi_shop.dao.MemberDao;

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

	
}
