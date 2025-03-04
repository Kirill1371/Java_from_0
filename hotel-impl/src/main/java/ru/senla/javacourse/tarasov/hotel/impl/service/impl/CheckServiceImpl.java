package ru.senla.javacourse.tarasov.hotel.impl.service.impl;

import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.GuestMapper;
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;
//import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.RoomRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.StayRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.CheckService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class CheckServiceImpl implements CheckService {

    @Inject
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private StayRepository stayRepository;

    @Override
    public void checkIn(int roomNumber, GuestDto guestDto, Date checkInDate, Date checkOutDate) {
        Room room = roomRepository.getRoom(roomNumber);

        if (room != null && room.getStatus().equalsIgnoreCase("Available")) {
            room.setStatus("Occupied");
            roomRepository.updateRoomStatus(roomNumber, "Occupied");

            // Преобразуем GuestDto в Guest
            Guest guest = GuestMapper.toEntity(guestDto);

            // Проверяем, существует ли гость в базе данных, если нет — сохраняем его
            Guest existingGuest = guestRepository.getGuest(guest.getName());
            if (existingGuest == null) {
                guestRepository.addGuest(guest); // Сохраняем гостя перед заселением
            } else {
                guest = existingGuest; // Используем уже существующего гостя
            }

            Stay stay = new Stay(guest, room, checkInDate, checkOutDate);
            stayRepository.addStay(stay); // Сохранение заселения в базу данных

            System.out.println("Checked in to room: " + roomNumber);
        } else {
            System.out.println("Room not available: " + roomNumber);
        }
    }


    @Override
    public void checkOut(int roomNumber) {
        Room room = roomRepository.getRoom(roomNumber);
        if (room != null) {
            roomRepository.setStatusAv(roomNumber);
            System.out.println("Checked out from room: " + roomNumber);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }
}