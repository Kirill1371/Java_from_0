package service;

import model.Room;
import model.Service;
import model.Guest;
import model.Stay;
import repository.IHotelRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HotelService implements IHotelService {
    @Inject
    private IHotelRepository hotelRepository;
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }


    public HotelService(IHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
        this.rooms = new ArrayList<>();
    }


    // Конструктор без параметров
    public HotelService() {
        // Инициализация по умолчанию, если необходимо
    }
    @Override
    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null && room.getStatus().equals("Available")) {
            room.setStatus("Occupied");
            Stay stay = new Stay(guest, room, checkInDate, checkOutDate);
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


    @Override
    public void importRoomsFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Начинается чтение файла: " + filePath);
            while ((line = reader.readLine()) != null) {
                System.out.println("Прочитана строка: " + line); // Вывод каждой строки
                if (line.trim().isEmpty()) {
                    System.out.println("Пропуск пустой строки");
                    continue;
                }

                String[] parts = line.split(",");
                System.out.println("Разбито на части: " + Arrays.toString(parts)); // Проверка разделения
                if (parts.length != 6) {
                    System.out.println("Ошибка: Ожидается 6 значений, но найдено " + parts.length);
                    continue;
                }

                try {
                    // Извлечение данных
                    String id = parts[0].trim(); // ID комнаты
                    int roomNumber = Integer.parseInt(parts[1].trim()); // Номер комнаты
                    String status = parts[2].trim(); // Статус комнаты (occupied, available)
                    double price = Double.parseDouble(parts[3].trim()); // Цена за номер
                    int capacity = Integer.parseInt(parts[4].trim()); // Вместимость
                    int stars = Integer.parseInt(parts[5].trim()); // Звёзды
                    
                    // Создание объекта Room
                    Room newRoom = new Room(id, roomNumber, status, price, capacity, stars);
                    hotelRepository.addRoom(newRoom);
                    System.out.println("Комната добавлена: " + newRoom);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка парсинга данных: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + filePath);
            e.printStackTrace();
        }
    }



    public void exportRoomsToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Записываем заголовок
            writer.write("id,number,status,price,capacity,stars");
            writer.newLine();
    
            for (Room room : hotelRepository.getAllRooms()) {
                // Записываем данные комнат
                writer.write(String.format("%s,%d,%s,%.2f,%d,%d",
                    room.getId(),
                    room.getNumber(),
                    room.getStatus(),
                    room.getPrice(),
                    room.getCapacity(),
                    room.getStars()
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
    

    @Override
    public void importGuestsFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Начинается чтение файла: " + filePath);

            while ((line = reader.readLine()) != null) {
                System.out.println("Прочитана строка: " + line);
                if (line.trim().isEmpty()) {
                    System.out.println("Пропуск пустой строки");
                    continue;
                }

                String[] parts = line.split(",");
                System.out.println("Разбито на части: " + Arrays.toString(parts));
                if (parts.length < 2) {
                    System.out.println("Ошибка: Ожидается минимум 2 значения (id и name)");
                    continue;
                }

                try {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    Guest newGuest = new Guest(name);
                    hotelRepository.addGuest(newGuest);
                    System.out.println("Гость добавлен: " + newGuest.getName());
                } catch (Exception e) {
                    System.out.println("Ошибка парсинга данных: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + filePath);
            e.printStackTrace();
        }
    }

    @Override
    public void exportGuestsToCSV(String filePath) {
        List<Guest> guests = hotelRepository.getAllGuests();

        if (guests == null || guests.isEmpty()) {
            System.out.println("No guests available to export.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("id,name");
            writer.newLine();

            for (Guest guest : guests) {
                StringBuilder csvLine = new StringBuilder();
                csvLine.append(guest.getId()).append(",").append(guest.getName());
                
                writer.write(csvLine.toString());
                writer.newLine();
            }

            System.out.println("Guests exported successfully to file: " + filePath);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public List<Stay> getRoomHistory(int roomNumber) {
        return hotelRepository.getRoomHistory(roomNumber);
    }

}
