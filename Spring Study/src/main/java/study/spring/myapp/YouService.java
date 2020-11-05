package study.spring.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YouService implements IYouService{
	
	@Autowired
	IYouRepository youRepository;
	
	@Override
	public String name(String name) {
		return youRepository.name(name);
	}

	@Override
	public String age(String age) {
		return youRepository.age(age);
	}

	@Override
	public String gender(String gender) {
		return youRepository.gender(gender);
	}

}
