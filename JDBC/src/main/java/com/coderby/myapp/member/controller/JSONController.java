package com.coderby.myapp.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderby.myapp.member.model.JSONVO;

@Controller
@RequestMapping("/json")
public class JSONController {
	
	@ResponseBody
	@RequestMapping(value="/ex", produces="application/text;charset=UTF-8")
	public String exam(JSONVO jsonVO, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return "name : "+jsonVO.getName()+", age : "+jsonVO.getAge();
	}
	
	
	@RequestMapping(value="/example", produces="application/text;charset=UTF-8")
	@ResponseBody
	public String example(String userId) {
		return "ajax 데이터 : " + userId;
	}
}
