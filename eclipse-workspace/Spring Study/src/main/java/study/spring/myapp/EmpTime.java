package study.spring.myapp;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmpTime {
	
	public static void timeLog() {
		System.out.println(new java.util.Date());
	}
	
}
