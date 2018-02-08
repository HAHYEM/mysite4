package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestbookDao;

	public List<GuestBookVo> getGB() { // java¶û java¶û ¿¬°áÇÒ¶§´Â ¾È ½áÁàµµ µÇ°í!!

		return guestbookDao.getList();
	}

	public void insert(GuestBookVo guestbookVo) { 
		guestbookDao.insert(guestbookVo);
	}
	
	public GuestBookVo apiInsert(GuestBookVo guestbookVo) { 
		int no = guestbookDao.apiInsert(guestbookVo);
		GuestBookVo gvo= guestbookDao.apiSelectNo(no);
		
		return gvo;
	}
	
	public int apiSelectNo(int no) {
		guestbookDao.apiSelectNo(no);
		return no;
	}
	
	public void delete(int no, String password) {
		guestbookDao.delete(no, password);
	}
	
	public boolean apiDelete(GuestBookVo guestbookVo) {
		boolean result = false;
		GuestBookVo pw = guestbookDao.apiSelectNo(guestbookVo.getNo());
		if(guestbookVo.getPassword().equals(pw.getPassword())){
			guestbookDao.apiDelete(guestbookVo.getNo());
			result = true;
		}else {
			result = false;
		}
		return result;
	}

	public List<GuestBookVo> getGuestbookListPage(int page) {
		return guestbookDao.selectGuestbookListPage(page);
	}
}
