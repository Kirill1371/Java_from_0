package controller;

import java.util.Date;
import model.Room;

public interface RoomController {
    public void addRoom(Room room);
    public void addRoomToDatabase(int roomNumber, double price, int capacity, int stars);
    public void removeRoomFromDatabase(int roomNumber);
    public void getRoomDetailsFromDB(int roomNumber);
    public void removeRoom(int roomNumber);
    public void setRoomStatus(int roomNumber, String status);
    public void setRoomPrice(int roomNumber, double price);
    public void listAllRooms();
    public void listAvailableRooms();
    public void listRoomsSortedByPrice();
    public void listRoomsSortedByCapacity();
    public void listRoomsSortedByStars();
    public void listAvailableRoomsSortedByPrice();
    public void listAvailableRoomsSortedByCapacity();
    public void listAvailableRoomsSortedByStars();
    public void getTotalAvailableRooms();
    public void listRoomsAvailableByDate(Date date);
    public void getRoomDetails(int roomNumber);
    public void importRooms(String filePath);
    public void exportRooms(String filePath);
    public void listLastThreeStays(int roomNumber); 
}

