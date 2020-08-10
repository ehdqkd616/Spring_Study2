package com.coderby.myapp.hello.aop;

import org.springframework.stereotype.Component;

@Component
public class RepositoryB implements IRepositoryB {

	@Override
	public String name(String name) {
		return "이름 : "+name;
	}

	@Override
	public String age(String age) {
		return "나이 : "+age;
	}

	@Override
	public String gender(String gender) {
		return "성별 : "+gender;
	}
	
	
	
}
