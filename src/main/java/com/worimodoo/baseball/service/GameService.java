package com.worimodoo.baseball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worimodoo.baseball.dao.GameDao;
import com.worimodoo.baseball.dto.GameDto;
import com.worimodoo.baseball.dto.Member;
import com.worimodoo.baseball.util.PathCodeInterface;
import com.worimodoo.baseball.util.ReturnCodeInterface;

@Service
public class GameService implements ReturnCodeInterface, PathCodeInterface {
	
	@Autowired
	private GameDao gameDao;

	public int insertStartGameData(Member member) {
		return gameDao.insertStartGameData(member);
	}
	
}
