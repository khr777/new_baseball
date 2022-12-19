package site.canco.baseball.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.canco.baseball.dao.TestDao;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;

	public Map<String, Object> getData() {
		return testDao.getData();
	}

}
