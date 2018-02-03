package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//주소 매핑을 할 수 있도록 annotation을 해줘야함 그래서 Controller를 달아주는 것이다.
@Controller
public class MainController {
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		
		return "main/index";	//main을 적지 않으면 views밑에서 찾기 때문에 에러가 생김
	}
	
}
