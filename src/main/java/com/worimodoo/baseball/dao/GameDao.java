package com.worimodoo.baseball.dao;

import org.apache.ibatis.annotations.Mapper;

import com.worimodoo.baseball.dto.GameDto;
import com.worimodoo.baseball.dto.Member;

@Mapper
public interface GameDao {

	int insertStartGameData(Member member);


}
