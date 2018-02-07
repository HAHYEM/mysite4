package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@ResponseBody
	@RequestMapping(value = "/gb/api/list", method = RequestMethod.POST)
	public List<GuestBookVo> apiList(@RequestParam("page") int page) {

		List<GuestBookVo> gList = guestbookService.getGuestbookListPage(page);
		System.out.println(gList.toString());

		return gList;
	}

}
