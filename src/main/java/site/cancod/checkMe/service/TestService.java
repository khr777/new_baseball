package site.cancod.checkMe.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.cancod.checkMe.dao.TestDao;
import site.cancod.checkMe.vo.Article;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;

	public Article getData() {
		return testDao.getData();
	}

}
