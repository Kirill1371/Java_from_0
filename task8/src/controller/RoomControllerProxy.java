package controller;

import model.Room;
import java.util.Date;

public class RoomControllerProxy implements RoomController {
    private final RoomController roomController;

    public RoomControllerProxy(RoomController roomController) {
        this.roomController = roomController;
    }

    public void addRoom(Room room) {
        try {
            roomController.addRoom(room);
        } catch(Exception e) {
            System.out.println("404");
        }
    }

    public void removeRoom(int roomNumber) {
        try {
            roomController.removeRoom(roomNumber);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void setRoomStatus(int roomNumber, String status) {
        try {
            roomController.setRoomStatus(roomNumber, status);
        }
        catch (Exception e) {
            System.out.println("404");
        }
    }

    public void setRoomPrice(int roomNumber, double price) {
        try {
            roomController.setRoomPrice(roomNumber, price);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listAllRooms() {
        try {
            roomController.listAllRooms();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listAvailableRooms() {
        try {
            roomController.listAvailableRooms();
        } catch (Exception e) {
            System.out.println("404");
        }
    }
  
    public void listRoomsSortedByPrice() {
        try {
            roomController.listRoomsSortedByPrice();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listRoomsSortedByCapacity() {
        try {
            roomController.listRoomsSortedByCapacity();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listRoomsSortedByStars() {
        try {
            roomController.listRoomsSortedByStars();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listAvailableRoomsSortedByPrice() {
        try {
            roomController.listAvailableRoomsSortedByPrice();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listAvailableRoomsSortedByCapacity() {
        try {
            roomController.listAvailableRoomsSortedByCapacity();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listAvailableRoomsSortedByStars() {
        try {
            roomController.listAvailableRoomsSortedByStars();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listRoomsAvailableByDate(Date date) {
        try {
            roomController.listRoomsAvailableByDate(date);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void getRoomDetails(int roomNumber) {
        try {
            roomController.getRoomDetails(roomNumber);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void getTotalAvailableRooms() {
        try {
            roomController.getTotalAvailableRooms();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listLastThreeStays(int roomNumber) {
        try {
            roomController.listLastThreeStays(roomNumber);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void importRooms(String filePath) {
        try {
            roomController.importRooms(filePath);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void exportRooms(String filePath) {
        try {
            roomController.exportRooms(filePath);
        } catch (Exception e) {
            System.out.println("404");
        }
    }
}
