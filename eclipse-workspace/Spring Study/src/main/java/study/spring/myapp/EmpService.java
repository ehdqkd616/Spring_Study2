package study.spring.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmpService implements IEmpService {
	
	@Autowired
	IEmpRepository empRepository;

	@Override
	public String hello(String name) {
		return empRepository.hello(name);
	}

	@Override
	public String bye(String name) {
		return empRepository.bye(name);
	}

	@Override
	public String love(String name) {
		return empRepository.love(name);
	}
	
	
	
}
