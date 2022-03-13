package site.cancod.semi_shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TimeDao {

	int insertTimeName(@Param(value="timeName") String timeName, @Param(value="userId") String userId);
	
	
}
