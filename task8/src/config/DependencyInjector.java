package config;

import annotations.Component;
import annotations.Inject1;

import org.reflections.Reflections;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DependencyInjector {

    private static final Map<Class<?>, Object> dependencies = new HashMap<>();
    private static final Map<String, Object> Ndependencies = new HashMap<>();

        public DependencyInjector(String[] pack) {
            for (int i = 0; i< pack.length; i++) {
                scanAndRegisterComponents(pack[i]);
            }
            dependencies.forEach((key, value) -> System.out.println("Класс: " + key.getName()));
            Ndependencies.forEach((key, value) -> System.out.println("Именованный бин: " + key + ", Экземпляр: " + value));
        }

    public void scanAndRegisterComponents(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> componentClasses = reflections.getTypesAnnotatedWith(Component.class);
    
        System.out.println("Найдено классов с @Component: " + componentClasses.size());
        for (Class<?> clazz : componentClasses) {
            System.out.println("Обнаружен: " + clazz.getName());
            try {
                Object instance = createOrGetInstance(clazz);
                dependencies.put(clazz, instance);
    
                Component component = clazz.getAnnotation(Component.class);
                if (!component.value().isEmpty()) {
                    System.out.println("Регистрируем именованный бин: " + component.value());
                    Ndependencies.put(component.value(), instance);
                }
            } catch (Exception e) {
                System.err.println("Ошибка при создании экземпляра: " + clazz.getName());
                e.printStackTrace();
                throw new RuntimeException("Не удалось создать экземпляр: " + clazz.getName(), e);
            }
        }
    }
    
    private static Object createOrGetInstance(Class<?> clazz) throws Exception {
        if (dependencies.containsKey(clazz)) {
            return dependencies.get(clazz);
        }
    
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            // constructor.getAnnotations()[0].toString();
            // System.out.println(constructor.getAnnotations()[0].annotationType());
            // System.out.println(Inject1.class.getName());
            if (constructor.isAnnotationPresent(Inject1.class)) {
                System.out.println("Creating instance with dependencies for: " + clazz.getName());
                return createInstanceWithDependencies(constructor);
            }
        }

        throw new RuntimeException("No suitable constructor found for: " + clazz.getName());
    }
    
    
    public static <T> T getBean(Class<T> clazz) {
        T bean = (T) dependencies.get(clazz);
        if (bean == null) {
            throw new RuntimeException("No bean found for: " + clazz.getName());
        }
        return bean;
    }

    private static void injectDependencies(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject1.class)) {
                try {
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    Object dependency = dependencies.get(fieldType);
                    if (dependency == null) {
                        dependency = createOrGetInstance(fieldType);
                    }
                    System.out.println("Injecting dependency: " + fieldType.getName() +
                            " into " + instance.getClass().getName());
                    field.set(instance, dependency);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject dependency for field: " +
                            field.getName() + " in class: " + instance.getClass().getName(), e);
                }
            }
        }
    }

    private static Object createInstanceWithDependencies(Constructor<?> constructor) throws Exception {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
    
        System.out.println("PT:    " + parameterTypes.length + constructor.getName());
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = dependencies.get(parameterTypes[i]);
            if (parameters[i] == null) {
                throw new RuntimeException("No registered dependency found for: " + parameterTypes[i].getName());
            }
        }
        System.out.println("for:    " + parameterTypes.length + constructor.getName());
    
        Object instance = constructor.newInstance(parameters);
        System.out.println("Instance created with dependencies: " + instance.getClass().getName());

        injectDependencies(instance);
    
        dependencies.put(constructor.getDeclaringClass(), instance);
        return instance;
    }

    public static void injectIntoExistingObject(Object object) {
        injectDependencies(object);
    }
    
}