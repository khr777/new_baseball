package com.worimodoo.baseball.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.worimodoo.baseball.dto.TestDto;

@Mapper
public interface BallTestDao {

	TestDto getQueryTest();
	
}
