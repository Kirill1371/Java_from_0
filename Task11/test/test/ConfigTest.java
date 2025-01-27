package test;

import java.util.UUID;

import config.Configurator;
import model.Room;

public class ConfigTest {
    public static void main(String[] args) {
        // Создаём объект Room с параметрами
        Room room = new Room(UUID.randomUUID().toString(), 101, "Available", 150.0, 2, 4);

        // Применяем конфигурирование с помощью аннотации
        Configurator.configure(room);

        // Выводим значения, которые были автоматически настроены из конфигурационного файла
        System.out.println("Room ID: " + room.getId());
        System.out.println("Room Status: " + room.getStatus());
        System.out.println("Room Price: " + room.getPrice());
        System.out.println("Room Capacity: " + room.getCapacity());
    }
}
