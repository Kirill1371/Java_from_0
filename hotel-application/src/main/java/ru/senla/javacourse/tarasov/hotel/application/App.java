//package ru.senla.javacourse.tarasov.hotel.application;
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import ru.senla.javacourse.tarasov.hotel.ioc.DependencyInjector;
//import ru.senla.javacourse.tarasov.hotel.ui.ConsoleUI;
//
//public class App {
//    private static final Logger logger = LogManager.getLogger(App.class);
//    public static void main(String[] args) {
////        DependencyInjector injector = new DependencyInjector(
////                "ru.senla.javacourse.tarasov.hotel.db.entity",
////                "ru.senla.javacourse.tarasov.hotel.impl",
////                "ru.senla.javacourse.tarasov.hotel.ioc",
////                "ru.senla.javacourse.tarasov.hotel.ui"
////        ).init();
//
//        DependencyInjector injector = new DependencyInjector("ru.senla.javacourse.tarasov").init();
//        injector.getBean(ConsoleUI.class).start();
//    }
//}



//package ru.senla.javacourse.tarasov.hotel.application;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import ru.senla.javacourse.tarasov.hotel.impl.AppConfig;
//import ru.senla.javacourse.tarasov.hotel.ui.ConsoleUI;
//
//public class App {
//    public static void main(String[] args) {
//        //ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.senla.javacourse.tarasov.hotel");
//        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
//        consoleUI.start();
//    }
//}


//package ru.senla.javacourse.tarasov.hotel.application;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//@SpringBootApplication
//public class App extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(App.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }
//}



package ru.senla.javacourse.tarasov.hotel.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.senla.javacourse.tarasov.hotel")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}