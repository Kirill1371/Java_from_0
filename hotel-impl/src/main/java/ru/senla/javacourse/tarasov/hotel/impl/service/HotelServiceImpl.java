package ru.senla.javacourse.tarasov.hotel.impl.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.StayDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepositoryImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

// todo ненадо никаких коментарие типа Room, надо разделить на несколько сервисов
@Component
public class HotelServiceImpl implements HotelService {
    @Inject
    private HotelRepositoryImpl hotelRepository;

    // todo что это за лист?
//    private List<Room> Rooms;

//    public List<Room> getRooms() {
//        return Rooms;
//    }

    @Inject
    public HotelServiceImpl(HotelRepositoryImpl hotelRepository) {
        this.hotelRepository = hotelRepository;
//        this.Rooms = new ArrayList<>();
    }


//////////////////////////////// Room ///////////////////////////////////////////
   
    @Override
    public void addRoomToDatabase(RoomDto roomDto) {
        Room room = RoomMapper.toEntity(roomDto);
        hotelRepository.addRoomToDatabase(room);
    }
    
    @Override
    public void removeRoomFromDatabase(int roomNumber) {
        hotelRepository.removeRoomFromDatabase(roomNumber);
        System.out.println("Room removed: " + roomNumber);
    }
    
    @Override
    public void setRoomStatus(int roomNumber, String status) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus(status);
            System.out.println("Room status updated: " + roomNumber + " to " + status);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void setRoomPrice(int roomNumber, double price) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setPrice(price);
            System.out.println("Room price updated: " + roomNumber + " to " + price);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return hotelRepository.getAllRooms();
    }

    @Override
    public List<RoomDto> getAvailableRooms() {
        return hotelRepository.getAvailableRooms();
    }


    @Override
    public List<RoomDto> getRoomsSortedByPrice() {
        return hotelRepository.getAllRooms().stream()
        .sorted((r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice()))
        .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoomsSortedByCapacity() {
        return hotelRepository.getAllRooms().stream()
        .sorted((r1, r2) -> Integer.compare(r1.getCapacity(), r2.getCapacity()))
        .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoomsSortedByStars() {
        return hotelRepository.getAllRooms().stream()
        .sorted((r1, r2) -> Integer.compare(r1.getStars(), r2.getStars()))
        .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRoomsSortedByPrice() {
        return getAvailableRooms().
        stream()
        .sorted((r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice()))
        .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRoomsSortedByCapacity() {
        return getAvailableRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getCapacity(), r2.getCapacity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRoomsSortedByStars() {
        return getAvailableRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getStars(), r2.getStars()))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalAvailableRooms() {
        return (int) hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available"))
                .count();
    }

    @Override
    public List<RoomDto> getRoomsAvailableByDate(Date date) {
        return hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available") || room.getStays().stream().anyMatch(stay -> stay.getCheckOutDate().before(date)))
                .collect(Collectors.toList());
    }

    @Override
    public Room getRoomDetails(int roomNumber) {
        return hotelRepository.getRoomFromDatabase(roomNumber);
    }


/////////////////////////     Check /////////////////////////////////////////////////////////////////

    @Override
    public void checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate) {
        Room room = hotelRepository.getRoom(roomNumber);

        if (room != null && room.getStatus().equalsIgnoreCase("Available")) {
            room.setStatus("Occupied");
            hotelRepository.updateRoomStatus(roomNumber, "Occupied");

            // Проверяем, существует ли гость в базе данных, если нет — сохраняем его
            Guest existingGuest = hotelRepository.getGuestByName(guest.getName());
            if (existingGuest == null) {
                hotelRepository.addGuest(guest); // Сохраняем гостя перед заселением
            } else {
                guest = existingGuest; // Используем уже существующего гостя
            }

            Stay stay = new Stay(guest, room, checkInDate, checkOutDate);
            hotelRepository.addStay(stay); // Сохранение заселения в базу данных

            System.out.println("Checked in to room: " + roomNumber);
        } else {
            System.out.println("Room not available: " + roomNumber);
        }
    }


    @Override
    public void checkOut(int roomNumber) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            hotelRepository.setStatusAv(roomNumber);
            System.out.println("Checked out from room: " + roomNumber);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void addService(String guestName, ServiceDto service) {
        // Передаём имя гостя и услугу в репозиторий
        hotelRepository.addService(guestName, service);
        System.out.println("Service added for guest: " + guestName + ", Service: " + service.getName());
    }


    @Override
    public List<GuestDto> getAllGuests() {
        return hotelRepository.getAllGuests();
    }

    @Override
    public List<GuestDto> getGuestsSortedByName() {
        return hotelRepository.getAllGuests().stream()
                .sorted((g1, g2) -> g1.getName().compareTo(g2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestDto> getGuestsSortedByCheckOutDate() {
        return hotelRepository.getAllGuests().stream()
                .sorted((g1, g2) -> {
                    Date d1 = g1.getStays().get(g1.getStays().size() - 1).getCheckOutDate();
                    Date d2 = g2.getStays().get(g2.getStays().size() - 1).getCheckOutDate();
                    return d1.compareTo(d2);
                })
                .collect(Collectors.toList());
    }

 
    @Override
    public int getTotalGuests() {
        return hotelRepository.getAllGuests().size();
    }

   

    @Override
    public double getTotalPaymentForGuest(String guestName) {
        Guest guest = hotelRepository.getGuest(guestName);
        if (guest == null) {
            return 0;
        }
        return guest.getStays().stream()
                .mapToDouble(stay -> stay.getRoom().getPrice() * (stay.getCheckOutDate().getTime() - stay.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24))
                .sum();
    }

    @Override
    public List<StayDto> getLastThreeStays(int roomNumber) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room == null) {
            return new ArrayList<>();
        }
        return room.getStays().stream()
                .sorted((s1, s2) -> s2.getCheckOutDate().compareTo(s1.getCheckOutDate()))
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> getGuestServicesSortedByPrice(String guestName) {
        Guest guest = hotelRepository.getGuest(guestName);
        if (guest == null) {
            return new ArrayList<>();
        }
        return guest.getServices().stream()
                .sorted((s1, s2) -> Double.compare(s1.getPrice(), s2.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> getGuestServicesSortedByDate(String guestName) {
        Guest guest = hotelRepository.getGuest(guestName);
        if (guest == null) {
            return new ArrayList<>();
        }
        return guest.getServices().stream()
                .sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate()))
                .collect(Collectors.toList());
    }
}
