package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> bList = boardService.getList();
		model.addAttribute("bList", bList);
		return "board/list";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform(Model model) {
		List<BoardVo> bList = boardService.getList();
		model.addAttribute("bList", bList);
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		boardService.write(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String getArticle(Model model, @RequestParam("no") int no) {
		
		BoardVo view = boardService.view(no);
		System.out.println(view);
		model.addAttribute("boardVo", view);
		return "board/view";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Model model, @RequestParam("no") int no) {
		boardService.delete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(Model model, @RequestParam("no") int no) {
		BoardVo boardVo = boardService.getArticle(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@ModelAttribute BoardVo boardVo, @RequestParam("modify") int no) {
		
		boardVo.setNo(no);
		boardService.modify(boardVo);
		System.out.println("modify ÇÔ¼ö:"+no);

		return "redirect:/board/view?no="+no;
	}
	
	
	
	
	
}
