package site.canco.baseball.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

	Map<String, Object> getData();

}
