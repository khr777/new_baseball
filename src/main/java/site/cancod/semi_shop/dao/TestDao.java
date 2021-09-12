package site.cancod.semi_shop.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import site.cancod.semi_shop.vo.Article;

@Mapper
public interface TestDao {

	Article getData();

}
