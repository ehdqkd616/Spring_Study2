package study.spring.myapp;

import org.springframework.stereotype.Component;

@Component
public class YouRepository implements IYouRepository {

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
