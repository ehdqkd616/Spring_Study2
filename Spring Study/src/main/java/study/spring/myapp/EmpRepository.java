package study.spring.myapp;

import org.springframework.stereotype.Repository;

@Repository
public class EmpRepository implements IEmpRepository {

	@Override
	public String hello(String name) {
		return "hello~~"+name;
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
