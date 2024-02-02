package com.worimodoo.baseball.dao;

import org.apache.ibatis.annotations.Mapper;

import com.worimodoo.baseball.dto.Member;

@Mapper
public interface MemberDao {

	int joinMember(Member member);

	int getNickNameOverlapCount(String nickName);

}
