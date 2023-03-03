package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.cli.Menu;

@SpringBootApplication
public class SchoolHibernateCliApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SchoolHibernateCliApplication.class);
        Menu menu = applicationContext.getBean(Menu.class);
        menu.initMenu();
    }
    
}