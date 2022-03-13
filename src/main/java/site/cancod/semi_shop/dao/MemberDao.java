package site.cancod.semi_shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

	int insertJoin(@Param(value = "loginId") String loginId, @Param(value="loginPw")String loginPw, @Param(value="name") String name, @Param(value="email") String email);

	int getCheckLoginId(@Param(value="loginId") String loginId);

}
