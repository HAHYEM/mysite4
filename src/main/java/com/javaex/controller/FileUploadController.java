package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("/form")
	public String form(Model model) {
		String url = "upload/";
		List<GalleryVo> gList = fileUploadService.getList();
		
		model.addAttribute("gList", gList);
		model.addAttribute("url", url);
		
		return "fileupload/form";
	}
	
	@RequestMapping("/upload")
	public String uploade(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println(file.toString());
		String saveName = fileUploadService.restore(file);
		
		String url = "upload/" + saveName;
		model.addAttribute("url", url);
		
		return "fileupload/result";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam ("no")int no, Model model) {
		fileUploadService.delete(no);
		return "redirect:/fileupload/form";
	}
}
