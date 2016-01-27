package ro.sok.geolocation.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "ro.sok.geolocation.dao", "ro.sok.geolocation.service"})
@Import(HibernateConfiguration.class)
public class AppConfig {

}
