package ro.sok.geolocation.main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ro.sok.geolocation.service.SearchService;
import ro.sok.geolocation.spring.AppConfig;

public class AppStarter {
	public static void main(String[] args) {
		 AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 
		 SearchService ss = context.getBean(SearchService.class);
		 
//		 ss.reindex();
		 
//		 GeoEntity ge = new GeoEntity();
//		 ge.setLat(44.93);
//		 ge.setLon(26.05);
//		 ge.setValue("Curva");
//		 ss.addEntry(ge);
		 
		 List list = ss.search(44.71, 25.3, "profesor");
		 System.out.println(list);
	}
}
