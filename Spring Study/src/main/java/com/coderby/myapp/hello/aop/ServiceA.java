package com.coderby.myapp.hello.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ServiceA implements IServiceA {
	
	@Autowired
	IRepositoryA repositoryA;

	@Override
	public void introduce() {
		repositoryA.introduce();		
	}

	@Override
	public String bye(String name) {
		return repositoryA.bye(name);
	}

	@Override
	public String love(String name) {
		return repositoryA.love(name);
	}


	
	
	
}
