package ru.senla.javacourse.tarasov.hotel.ioc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})  // Теперь можно использовать и для классов
public @interface ConfigProperty {
    String configFileName() default "config.properties";  // Имя конфигурационного файла
    String propertyName() default ""; // По умолчанию будет генерироваться как КЛАСС.ПОЛЕ
    Class<?> type() default String.class; // Тип данных (по умолчанию String)
}
