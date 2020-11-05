package com.coderby.myapp.hello.aop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import study.spring.myapp.IEmpService;
import study.spring.myapp.IYouService;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
//		IServiceA serviceA = con.getBean("serviceA", IServiceA.class);
//		serviceA.introduce();
//		System.out.println(serviceA.bye("김연우"));
//		System.out.println(serviceA.love("김연우"));
		
		IServiceB serviceB = con.getBean("serviceB", IServiceB.class);
		System.out.println(serviceB.name("김연우"));
		System.out.println(serviceB.age("24"));
		System.out.println(serviceB.gender("남"));

	}

}
