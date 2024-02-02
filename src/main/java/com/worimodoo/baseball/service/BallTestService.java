package com.worimodoo.baseball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worimodoo.baseball.dao.BallTestDao;
import com.worimodoo.baseball.dto.TestDto;

@Service
// public class BallTestServiceImpl implements BallTestService {
public class BallTestService  {
	
	@Autowired
	private BallTestDao ballTestDao;
	
	
	public TestDto getQueryTest() {
		return ballTestDao.getQueryTest();
	} 
}
