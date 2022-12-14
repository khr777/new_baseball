package site.cancod.checkMe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import site.cancod.checkMe.vo.TimeName;

@Mapper
public interface TimeDao {

	int insertTimeName(@Param(value="timeName") String timeName, @Param(value="userId") String userId);

	List<TimeName> getAllTimeNameList(@Param(value="userId") String userId);
	
	
}
