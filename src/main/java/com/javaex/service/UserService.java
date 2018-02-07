package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public UserVo login(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public UserVo logout(int no) {
		return userDao.getUser(no);
	}
	
	public boolean emailCheck(String email) {
		boolean result;
		UserVo userVo = userDao.getUser(email);
		if(userVo != null) {
			result = false;
		}else {
			result = true;
		}
		return result;
		
	}
	
	public void join(UserVo userVo) {
		userDao.insert(userVo);
	}
	
	public UserVo updateForm(int no) {
		UserVo user = userDao.getUser(no);
		return user;
	}
	
	public void update(UserVo userVo) {
		userDao.update(userVo);
	}
}
