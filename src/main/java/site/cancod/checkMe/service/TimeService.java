package site.cancod.checkMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.cancod.checkMe.dao.TimeDao;
import site.cancod.checkMe.vo.TimeName;

@Service
public class TimeService {

	@Autowired
	private TimeDao timeDao;

	public int insertTimeName(String timeName, String userId) {
		return timeDao.insertTimeName(timeName, userId);
	}

	public List<TimeName> getAllTimeNameList(String userId) {
		return timeDao.getAllTimeNameList(userId);
	}
	
}
