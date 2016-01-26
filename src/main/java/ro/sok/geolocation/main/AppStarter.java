package ro.sok.geolocation.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ro.sok.geolocation.spring.AppConfig;

public class AppStarter {
	public static void main(String[] args) {
		 AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
