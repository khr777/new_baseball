package site.cancod.checkMe.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import site.cancod.checkMe.vo.Article;

@Mapper
public interface TestDao {

	Article getData();

}
