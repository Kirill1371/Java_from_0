//package ru.senla.javacourse.tarasov.hotel.impl.repository;
//
//import java.util.List;
//import java.util.UUID;
//import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
//import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
//import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
//import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
//
//// todo разделить на репозитории RoomRepository, ServiceRepository и т.д.
//public interface RoomRepository {
//    //room
//    void addRoom(Room room);
//    Room getRoom(int roomNumber);
//    List<Room> getAllRooms();
//    List<Room> getAvailableRooms();
//    //room
//
//    void addService(String guestName, Service service);
//    List<Service> getServicesForGuest(String guestName);
//
//    void addStay(Stay stay);
//    List<Stay> getAllStays();
//
//    //room
//    void setStatusAv(int roomNumber);
//    Room getRoomById(UUID id);
//    void updateRoomStatus(int roomNumber, String newStatus);
//    //
//
//    Guest getGuest(String name);
//    List<Guest> getAllGuests();
//    // todo этого метода небыло, но он использовался CheckServiceImpl:36
//    void addGuest(Guest guest);
//
//
//    //room
//    // todo этого метода небыло, но он использовался RoomServiceImpl:31
//    void removeRoom(int roomNumber);
//}
