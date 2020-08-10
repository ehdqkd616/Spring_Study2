package com.coderby.myapp.hello.aop;

import java.util.Scanner;

import org.springframework.stereotype.Repository;

@Repository
public class RepositoryA implements IRepositoryA {

	@Override
	public void introduce() {
		Scanner scan = new Scanner(System.in);
		
        String name;
        int age;
        String gender;
        
        System.out.print("이름 : ");
        name = scan.next();
 
        System.out.print("나이 : ");
        age = scan.nextInt();
        
        System.out.println("성별 : ");
        gender = scan.next();
        System.out.println("이름 : "+name +"나이 : "+age+"성별 : "+gender); 
	}
	
	@Override
	public String bye(String name) {
		return "bye~~"+name;
	}

	@Override
	public String love(String name) {
		return "I love you~~"+name;
	}
	
}
