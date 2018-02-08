package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestBookService guestbookService;

	@ResponseBody //body 안에 내가 준 데이터를 넣어서 보내라 
	@RequestMapping(value = "/gb/api/list", method = RequestMethod.POST)
	public List<GuestBookVo> apiList(@RequestParam("page") int page) {

		List<GuestBookVo> gList = guestbookService.getGuestbookListPage(page);
		System.out.println(gList.toString());

		return gList;
	}
	@ResponseBody
	@RequestMapping(value="/gb/api/insert", method = RequestMethod.POST)
	public GuestBookVo apiInsert(@RequestBody GuestBookVo guestbookVo) {
		System.out.println(guestbookVo.toString());
		GuestBookVo gvo = guestbookService.apiInsert(guestbookVo);
		System.out.println(gvo.toString());
		return gvo;
	}
	
	@ResponseBody
	@RequestMapping(value="/gb/api/select", method = RequestMethod.POST)
	public int apiSelectNo(@RequestParam("no") int no) {
		guestbookService.apiSelectNo(no);
		return no;
	}
	
	@ResponseBody
	@RequestMapping(value="/gb/api/delete", method = RequestMethod.POST)
	public boolean apiDelete(@RequestBody GuestBookVo guestbookVo) {
		boolean gvo = guestbookService.apiDelete(guestbookVo);
		System.out.println(gvo);
		return gvo;
	}
}
