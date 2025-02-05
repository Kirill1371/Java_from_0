package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Configurator {

    public static void configure(Object target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
                String configFileName = annotation.configFileName();
                String propertyName = annotation.propertyName();
                if (propertyName.isEmpty()) {
                    propertyName = clazz.getSimpleName() + "." + field.getName();  // Генерация по умолчанию
                }

                // Загружаем файл конфигурации
                Properties properties = loadProperties(configFileName);

                // Получаем значение из файла
                String value = properties.getProperty(propertyName);
                if (value != null) {
                    // Преобразуем значение в нужный тип
                    Object convertedValue = convertValue(value, annotation.type());
                    try {
                        field.setAccessible(true);
                        field.set(target, convertedValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to set value for field: " + field.getName(), e);
                    }
                }
            }
        }
    }

    private static Properties loadProperties(String configFileName) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("X:/SENLA/Hotel/src/resources/database/database.properties")) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found at specified path");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + configFileName, e);
        }
        return properties;
    }


    private static Object convertValue(String value, Class<?> type) {
        if (type == String.class) {
            return value;
        } else if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            throw new RuntimeException("Unsupported type: " + type);
        }
    }
}
