// package test;

// import controller.HotelController;
// import model.Room;
// import model.Service;
// import model.Guest;
// import repository.HotelRepository;
// import service.HotelService;
// import java.util.Date;

// public class HotelManagementTest {
//     public static void main(String[] args) {
//         HotelRepository hotelRepository = new HotelRepository();
//         HotelService hotelService = new HotelService(hotelRepository);
//         HotelController hotelController = new HotelController(hotelService);

//         // Добавление номеров
//         hotelController.addRoom(new Room(101, 100.0, 2, 3));
//         hotelController.addRoom(new Room(102, 150.0, 3, 4));

//         // Добавление услуг
//         hotelController.addService(new Service("WiFi", 10.0, "Internet"));
//         hotelController.addService(new Service("Breakfast", 20.0, "Food"));

//         // Поселение в номер
//         Guest guest1 = new Guest("John Doe");
//         hotelController.checkIn(101, guest1, new Date(), new Date(System.currentTimeMillis() + 86400000L)); // 1 day stay

//         // Изменение статуса номера
//         hotelController.setRoomStatus(102, "Under Maintenance");

//         // Изменение цены номера
//         hotelController.setRoomPrice(101, 120.0);

//         // Выселение из номера
//         hotelController.checkOut(101);

//         // Удаление номера
//         hotelController.removeRoom(102);

//         // Список всех номеров
//         hotelController.listAllRooms();

//         // Список свободных номеров
//         hotelController.listAvailableRooms();

//         // Список всех гостей
//         hotelController.listAllGuests();

//         // Сортировка номеров по цене
//         hotelController.listRoomsSortedByPrice();

//         // Сортировка номеров по вместимости
//         hotelController.listRoomsSortedByCapacity();

//         // Сортировка номеров по количеству звезд
//         hotelController.listRoomsSortedByStars();

//         // Сортировка свободных номеров по цене
//         hotelController.listAvailableRoomsSortedByPrice();

//         // Сортировка свободных номеров по вместимости
//         hotelController.listAvailableRoomsSortedByCapacity();

//         // Сортировка свободных номеров по количеству звезд
//         hotelController.listAvailableRoomsSortedByStars();

//         // Сортировка гостей по имени
//         hotelController.listGuestsSortedByName();

//         // Сортировка гостей по дате освобождения номера
//         hotelController.listGuestsSortedByCheckOutDate();

//         // Общее число свободных номеров
//         hotelController.getTotalAvailableRooms();

//         // Общее число постояльцев
//         hotelController.getTotalGuests();

//         // Список номеров, которые будут свободны по определенной дате
//         hotelController.listRoomsAvailableByDate(new Date(System.currentTimeMillis() + 172800000L)); // 2 days from now

//         // Сумма оплаты за номер, которую должен оплатить постоялец
//         hotelController.getTotalPaymentForGuest("John Doe");

//         // Последние три пребывания в номере
//         hotelController.listLastThreeStays(101);

//         // Список услуг постояльца, отсортированный по цене
//         hotelController.listGuestServicesSortedByPrice("John Doe");

//         // Список услуг постояльца, отсортированный по дате
//         hotelController.listGuestServicesSortedByDate("John Doe");

//         // Список услуг, отсортированный по категории и цене
//         hotelController.listServicesSortedByCategoryAndPrice();

//         // Детали отдельного номера
//         hotelController.getRoomDetails(101);
//     }
// }



package test;

import ui.SingletonUI;

public class HotelManagementTest {
    public static void main(String[] args) {
        SingletonUI singletonUI = SingletonUI.getInstance();
        singletonUI.getConsoleUI().start();
    }
}
