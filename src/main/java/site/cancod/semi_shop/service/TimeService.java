package site.cancod.semi_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.cancod.semi_shop.dao.TimeDao;

@Service
public class TimeService {

	@Autowired
	private TimeDao timeDao;

	public int insertTimeName(String timeName, String userId) {
		return timeDao.insertTimeName(timeName, userId);
	}
	
}
