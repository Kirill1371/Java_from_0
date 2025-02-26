package ru.senla.javacourse.tarasov.hotel.impl.service;





import java.util.Date;
import java.util.List;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.StayDto;

public interface HotelService {
    void checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate);
    void checkOut(int roomNumber);
    void setRoomStatus(int roomNumber, String status);
    void setRoomPrice(int roomNumber, double price);
    void addRoomToDatabase(int roomNumber, double price, int capacity, int stars);
    void removeRoomFromDatabase(int roomNumber);
    void addService(String guestname, ServiceDto service);
    List<RoomDto> getAllRooms();
    List<RoomDto> getAvailableRooms();
    List<GuestDto> getAllGuests();
    List<RoomDto> getRoomsSortedByPrice();
    List<RoomDto> getRoomsSortedByCapacity();
    List<RoomDto> getRoomsSortedByStars();
    List<RoomDto> getAvailableRoomsSortedByPrice();
    List<RoomDto> getAvailableRoomsSortedByCapacity();
    List<RoomDto> getAvailableRoomsSortedByStars();
    List<GuestDto> getGuestsSortedByName();
    List<GuestDto> getGuestsSortedByCheckOutDate();
    int getTotalAvailableRooms();
    int getTotalGuests();
    List<RoomDto> getRoomsAvailableByDate(Date date);
    double getTotalPaymentForGuest(String guestName);
    List<StayDto> getLastThreeStays(int roomNumber);
    List<ServiceDto> getGuestServicesSortedByPrice(String guestName);
    List<ServiceDto> getGuestServicesSortedByDate(String guestName);
    RoomDto getRoomDetails(int roomNumber);
}


