package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestBookVo;
@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestbookDao;
	
	public List<GuestBookVo> getGB() {	//java¶û java¶û ¿¬°áÇÒ¶§´Â ¾È ½áÁàµµ µÇ°í!!

		return guestbookDao.getList();
	}

	public void insert(GuestBookVo guestbookVo) { 	//
		guestbookDao.insert(guestbookVo);
	}

	public void delete(int no, String password) {
		guestbookDao.delete(no, password);		
	}

}
