package site.cancod.semi_shop.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.cancod.semi_shop.dao.TestDao;
import site.cancod.semi_shop.vo.Article;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;

	public Article getData() {
		return testDao.getData();
	}

}
