package test;

import config.DependencyInjector;
import controller.RoomController;
import controller.RoomControllerIMPL;
import repository.HotelRepository;
import service.HotelService;
import ui.handler.AddRoomHandler;

public class DIExample {
    public static void main(String[] args) {
        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);
        // Создаём контроллер
        RoomControllerIMPL roomController = new RoomControllerIMPL(hotelService);

        // Внедряем зависимости
        DependencyInjector.injectDependencies(roomController);

        // Пример использования
        new AddRoomHandler(roomController);
        //roomController.addRoom();  // Должно напечатать "Adding a new room..."
    }
}
