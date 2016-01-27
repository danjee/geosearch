package ro.sok.geolocation.main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ro.sok.geolocation.entity.GeoEntity;
import ro.sok.geolocation.service.SearchService;
import ro.sok.geolocation.spring.AppConfig;

public class AppStarter {
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		SearchService ss = context.getBean(SearchService.class);

		// ss.reindex();

		// GeoEntity ge = new GeoEntity();
		// ge.setLat(44.449418);
		// ge.setLon(26.148979);
		// ge.setValue("Profesor din baicului");
		// ss.addEntry(ge);
		//
		List<GeoEntity> list = ss.search(44.390768, 26.152931, 0, 2, "profesor");
		for (GeoEntity ge : list){
			System.out.println(ge);
		}
		
	}
}
