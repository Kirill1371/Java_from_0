package config;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyInjector {

    private static final Map<Class<?>, Object> dependencies = new HashMap<>();

    public static void registerDependency(Class<?> type, Object instance) {
        dependencies.put(type, instance);
    }

    public static void injectDependencies(Object target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    Object dependency = getDependency(fieldType);
                    field.set(target, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency for field: " + field.getName(), e);
                }
            }
        }
    }

    private static Object getDependency(Class<?> type) {
        if (dependencies.containsKey(type)) {
            return dependencies.get(type);
        }
        try {
            Object instance = type.getDeclaredConstructor().newInstance();
            dependencies.put(type, instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create dependency of type: " + type.getName(), e);
        }
    }
}
