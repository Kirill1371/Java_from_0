package config;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyInjector {

    private static final Map<Class<?>, Object> dependencies = new HashMap<>();

    public static <T> void registerDependency(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            injectDependencies(instance); 
            dependencies.put(clazz, instance);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register class: " + clazz.getName(), e);
        }
    }

    public static <T> void registerDependency(Class<T> interfaceClass, T implementation) {
        dependencies.put(interfaceClass, implementation);
    }

    public static <T> T getBean(Class<T> clazz) {
        if (!dependencies.containsKey(clazz)) {
            throw new RuntimeException("No dependency registered for: " + clazz.getName());
        }
        return clazz.cast(dependencies.get(clazz));
    }

    public static void injectDependencies(Object target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    Object dependency = getBean(fieldType); 
                    field.set(target, dependency); 
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency for field: " + field.getName(), e);
                }
            }
        }
    }
}

