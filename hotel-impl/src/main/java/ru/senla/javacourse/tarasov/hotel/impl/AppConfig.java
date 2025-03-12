package ru.senla.javacourse.tarasov.hotel.impl;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;
import ru.senla.javacourse.tarasov.hotel.impl.repository.impl.RoomRepositoryImpl;
import ru.senla.javacourse.tarasov.hotel.impl.service.impl.RoomServiceImpl;

@Configuration
@PropertySource("classpath:application.properties") // Указываем путь к конфигурационному файлу
public class AppConfig {

    @Bean
    public RoomRepositoryImpl roomRepository(SessionFactory sessionFactory) {
        return new RoomRepositoryImpl(sessionFactory);
    }

    @Bean
    public RoomServiceImpl roomService(RoomRepositoryImpl roomRepository) {
        return new RoomServiceImpl(roomRepository);
    }

    @Bean
    public RoomControllerImpl roomController(RoomServiceImpl roomService) {
        return new RoomControllerImpl(roomService);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}