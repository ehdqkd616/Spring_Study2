package study.spring.myapp;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface MyAnnotation {
	
	String name() default "기본값";//default를 안넣으면 null값 들어감
	
}
