package com.poscodx.hellospring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@ResponseBody
	@RequestMapping("/hello")
	public Object hello(String name) {
		System.out.println(name);
		return new ArrayList<String>(); //"/WEB-INF/views/hello.jsp";
	}
	
	public void test() {
		System.out.println("qwerqwerqwerwqer");
	}
}
