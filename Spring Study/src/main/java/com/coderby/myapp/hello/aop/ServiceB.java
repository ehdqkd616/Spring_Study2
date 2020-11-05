package com.coderby.myapp.hello.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceB implements IServiceB{
	
	@Autowired
	IRepositoryB repositoryB;
	
	@Override
	public String name(String name) {
		return repositoryB.name(name);
	}

	@Override
	public String age(String age) {
		return repositoryB.age(age);
	}

	@Override
	public String gender(String gender) {
		return repositoryB.gender(gender);
	}

}
