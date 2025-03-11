package service;

import model.Room;
import model.Service;
import model.Guest;
import model.Stay;
import repository.IHotelRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class HotelService implements IHotelService {
    private IHotelRepository hotelRepository;

    public HotelService(IHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null && room.getStatus().equals("Available")) {
            room.setStatus("Occupied");
            Stay stay = new Stay(room, checkInDate, checkOutDate);
            guest.addStay(stay);
            hotelRepository.addGuest(guest);
            System.out.println("Checked in to room: " + roomNumber);
        } else {
            System.out.println("Room not available: " + roomNumber);
        }
    }

    @Override
    public void checkOut(int roomNumber) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus("Available");
            System.out.println("Checked out from room: " + roomNumber);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
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
    public void addRoom(Room room) {
        hotelRepository.addRoom(room);
        System.out.println("Room added: " + room.getNumber());
    }

    @Override
    public void removeRoom(int roomNumber) {
        hotelRepository.removeRoom(roomNumber);
        System.out.println("Room removed: " + roomNumber);
    }

    @Override
    public void addService(Service service) {
        hotelRepository.addService(service);
        System.out.println("Service added: " + service.getName());
    }

    @Override
    public List<Room> getAllRooms() {
        return hotelRepository.getAllRooms();
    }

    @Override
    public List<Room> getAvailableRooms() {
        return hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Guest> getAllGuests() {
        return hotelRepository.getAllGuests();
    }

    @Override
    public List<Room> getRoomsSortedByPrice() {
        return hotelRepository.getAllRooms().stream()
                .sorted((r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getRoomsSortedByCapacity() {
        return hotelRepository.getAllRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getCapacity(), r2.getCapacity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getRoomsSortedByStars() {
        return hotelRepository.getAllRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getStars(), r2.getStars()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAvailableRoomsSortedByPrice() {
        return getAvailableRooms().
        stream()
                .sorted((r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAvailableRoomsSortedByCapacity() {
        return getAvailableRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getCapacity(), r2.getCapacity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAvailableRoomsSortedByStars() {
        return getAvailableRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getStars(), r2.getStars()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Guest> getGuestsSortedByName() {
        return hotelRepository.getAllGuests().stream()
                .sorted((g1, g2) -> g1.getName().compareTo(g2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Guest> getGuestsSortedByCheckOutDate() {
        return hotelRepository.getAllGuests().stream()
                .sorted((g1, g2) -> {
                    Date d1 = g1.getStays().get(g1.getStays().size() - 1).getCheckOutDate();
                    Date d2 = g2.getStays().get(g2.getStays().size() - 1).getCheckOutDate();
                    return d1.compareTo(d2);
                })
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalAvailableRooms() {
        return (int) hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available"))
                .count();
    }

    @Override
    public int getTotalGuests() {
        return hotelRepository.getAllGuests().size();
    }

    @Override
    public List<Room> getRoomsAvailableByDate(Date date) {
        return hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available") || room.getStays().stream().anyMatch(stay -> stay.getCheckOutDate().before(date)))
                .collect(Collectors.toList());
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
    public List<Stay> getLastThreeStays(int roomNumber) {
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
    public List<Service> getGuestServicesSortedByPrice(String guestName) {
        Guest guest = hotelRepository.getGuest(guestName);
        if (guest == null) {
            return new ArrayList<>();
        }
        return guest.getServices().stream()
                .sorted((s1, s2) -> Double.compare(s1.getPrice(), s2.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Service> getGuestServicesSortedByDate(String guestName) {
        Guest guest = hotelRepository.getGuest(guestName);
        if (guest == null) {
            return new ArrayList<>();
        }
        return guest.getServices().stream()
                .sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Service> getServicesSortedByCategoryAndPrice() {
        return hotelRepository.getAllServices().stream()
                .sorted((s1, s2) -> {
                    int categoryComparison = s1.getCategory().compareTo(s2.getCategory());
                    if (categoryComparison != 0) {
                        return categoryComparison;
                    }
                    return Double.compare(s1.getPrice(), s2.getPrice());
                })
                .collect(Collectors.toList());
    }

    @Override
    public Room getRoomDetails(int roomNumber) {
        return hotelRepository.getRoom(roomNumber);
    }
}
