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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootApplication(scanBasePackages = "ru.senla.javacourse.tarasov.hotel")

//@SpringBootApplication
//@SpringBootApplication(scanBasePackages = {
//        "ru.senla.javacourse.tarasov.hotel.application", // ваш текущий пакет
//        "ru.senla.javacourse.tarasov.hotel.impl.controller", // пакет с контроллерами
//        "ru.senla.javacourse.tarasov.hotel.impl.security", // пакет с security
//        "ru.senla.javacourse.tarasov.hotel.impl.config"
//})
@SpringBootApplication(scanBasePackages = {
        "ru.senla.javacourse.tarasov.hotel.application", // ваш текущий пакет
        "ru.senla.javacourse.tarasov.hotel.impl.controller", // пакет с контроллерами
        "ru.senla.javacourse.tarasov.hotel.impl.security", // пакет с security
        "ru.senla.javacourse.tarasov.hotel.impl.config",
        "ru.senla.javacourse.tarasov.hotel.impl.service",
        "ru.senla.javacourse.tarasov.hotel.impl.repository"
})


@EnableJpaRepositories(basePackages = "ru.senla.javacourse.tarasov.hotel.impl.repository")
@EntityScan(basePackages = "ru.senla.javacourse.tarasov.hotel.db.entity")

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String newPassword = "admin123"; // Новый пароль
//        String encodedPassword = encoder.encode(newPassword);
//        System.out.println("Новый хеш: " + encodedPassword);

//        String rawPassword = "admin";
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String hash1 = "$2a$10$R1Zlx9fR83yZjlNmTWDP3uxhgHFJBpx1PLdg9/6UiuWnf./fC7/ie";
//        String hash2 = "$2a$10$QSY.YFYpubiPYRcsJWt8ougXg0s0Ho3W971iYRBfhLuws.6gpaOFS";
//
//        System.out.println(encoder.matches(rawPassword, hash1)); // true
//        System.out.println(encoder.matches(rawPassword, hash2)); // true
    }
}