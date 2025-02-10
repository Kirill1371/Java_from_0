package com.test.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)  // Применяется к классам
@Retention(RetentionPolicy.RUNTIME)  // Доступна на этапе выполнения
public @interface Component {
    String value() default ""; // Значение по умолчанию — пустая строка
}
