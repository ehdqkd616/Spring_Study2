package com.coderby.myapp.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTracer2 {
	
	@Pointcut(value="execution(* com..Service*.*(..))")
	private void ServicePointcut() {}
	
	@Around("ServicePointcut()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature s = joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println("[Log]Before : "+ methodName +" time check start");
		long startTime = System.nanoTime();
		Object result = null;
		try {
			result = joinPoint.proceed();
			System.out.println("[실행된 결과]"+result);
		}catch(Exception e) {
			System.out.println("[Log]Exception : "+e.getMessage());
		}finally {
			System.out.println("[Log]Finally : "+methodName+" End.");
		}
		long endTime = System.nanoTime();
		System.out.println("[Log]After : "+ methodName +" time check end");
		System.out.println("[Log]"+ methodName +" Processing time is "+(endTime-startTime)+"ns");
		return result;
	}
	
	
}
