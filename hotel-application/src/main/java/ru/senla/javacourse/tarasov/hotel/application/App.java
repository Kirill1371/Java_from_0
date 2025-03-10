package ru.senla.javacourse.tarasov.hotel.application;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.senla.javacourse.tarasov.hotel.ioc.DependencyInjector;
import ru.senla.javacourse.tarasov.hotel.ui.ConsoleUI;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {
//        DependencyInjector injector = new DependencyInjector(
//                "ru.senla.javacourse.tarasov.hotel.db.entity",
//                "ru.senla.javacourse.tarasov.hotel.impl",
//                "ru.senla.javacourse.tarasov.hotel.ioc",
//                "ru.senla.javacourse.tarasov.hotel.ui"
//        ).init();

        DependencyInjector injector = new DependencyInjector("ru.senla.javacourse.tarasov").init();
        injector.getBean(ConsoleUI.class).start();
    }
}



