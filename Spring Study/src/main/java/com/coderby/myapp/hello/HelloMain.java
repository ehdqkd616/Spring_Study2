package com.coderby.myapp.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.coderby.myapp.hello.controller.HelloController;

public class HelloMain {

	public static void main(String[] args) {
//		IHelloService helloService = new HelloService();
//		HelloController controller = new HelloController(helloService);
//		controller.hello("홍길동");
		
//		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
//		System.out.println(Arrays.toString(con.getBeanDefinitionNames()));
//		con.close();
		
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
		HelloController control = con.getBean("helloController", HelloController.class);
		control.hello("김연우");
		con.close();		
	}
}
