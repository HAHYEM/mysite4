package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/gb")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET )
	public String list(Model model) {		//java랑 jsp랑 연결해줄때 model을 쓰는 것임
		List<GuestBookVo> gList = guestbookService.getGB();
		model.addAttribute("gList", gList);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST )
	public String insert(@ModelAttribute GuestBookVo guestbookVo) {
		guestbookService.insert(guestbookVo);
		System.out.println(guestbookVo.toString());
		return "redirect:/gb/list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET )
	public String deleteform(Model model, @RequestParam("no") int no) {
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST )
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		guestbookService.delete(no, password);
		return "redirect:/gb/list";
	}

	
}
