package study.spring.myapp;

import java.lang.reflect.Field;

public class MyContainer {
	
//	private <T> T runAnnotation(T obj) throws IllegalArgumentException, IllegalAccessException {
//		Field[] fields = obj.getClass().getDeclaredFields();
//		for(Field field : fields) {
//			MyAnnotation anno = field.getAnnotation(MyAnnotation.class);
//			if(anno != null && field.getType() == String.class) {
//				field.setAccessible(true);
//				field.set(obj, anno.name());
//			}
//		}
//		return obj;
//	}
//	
//	public <T> T getInstance(Class<T> c) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
//		T obj = c.newInstance();
//		obj = runAnnotation(obj);
//		return obj;
//	}	
//}
	
	private <T> T runAnnotation(T obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields) {
			MyAnnotation anno = field.getAnnotation(MyAnnotation.class);
			if(anno != null) {
				field.setAccessible(true);
				field.set(obj, field.getType().newInstance());
			}
		}
		return obj;
	}
	
	public <T> T getInstance(Class<T> c) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		T obj = c.newInstance();
		obj = runAnnotation(obj);
		return obj;
	}
}

//인터페이스와 추상클래스까지 구현가능하게 숙제


