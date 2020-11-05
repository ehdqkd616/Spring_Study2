package com.coderby.myapp.hello.controller;

import org.springframework.stereotype.Controller;

import com.coderby.myapp.hello.service.HelloService;
import com.coderby.myapp.hello.service.IHelloService;

@Controller
public class HelloController {

	IHelloService helloService = new HelloService();
	
	public void setHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}
	
	public void hello(String name) {
		System.out.println("HelloController : "+helloService.sayHello(name));
	}

}