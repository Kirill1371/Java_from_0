package ru.senla.javacourse.tarasov.hotel.api.controller;


import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

public interface HotelController {
    // todo а чем это отличается от метода в RoomController?
    public void addRoom(RoomDto room);
} 
