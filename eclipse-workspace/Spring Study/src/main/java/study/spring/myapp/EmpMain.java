package study.spring.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class EmpMain {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
//		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
//		IEmpService empService = con.getBean("empService", IEmpService.class);
//		System.out.println(empService.hello("김연우"));

//		예제 3
//		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
//		IEmpService empService = con.getBean("empServiceProxy", IEmpService.class);
//		System.out.println(empService.hello("김연우"));
		
//		예제 4
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
		IEmpService empService = con.getBean("empService", IEmpService.class);
		System.out.println(empService.hello("김연우"));
		System.out.println(empService.bye("김연우"));
		System.out.println(empService.love("김연우"));
		
		IYouService youService = con.getBean("youService", IYouService.class);
		System.out.println(youService.name("김연우"));
		System.out.println(youService.age("24"));
		System.out.println(youService.gender("남"));
		
		
//		MyContainer container = new MyContainer();
//		MyData myData = container.getInstance(MyData.class);
//		컨테이너 예제
//		System.out.println(myData);
//		예제 2		
//		System.out.println(myData.empRepository.hello("김연우"));
		
		
	}

}
