package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String list(Model model,
					   @RequestParam(value = "crtPage", required=false, defaultValue="1") Integer crtPage,
					   @RequestParam(value = "searchValue", required=false, defaultValue="") String searchValue){			
		//키워드를 받고 넘겨 받은 내용을 getList() 안에 집어넣어서 (boardService에서 )처리
		Map<String, Object> bmap = boardService.getList(searchValue, crtPage);
		model.addAttribute("bmap", bmap);
		return "board/list";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform(Model model) {
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
		System.out.println("modify:"+no);

		return "redirect:/board/view?no="+no;
	}
	
	
	
	
	
}
