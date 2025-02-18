//package ui.handler;
//
//import java.util.Scanner;
//import controller.RoomControllerIMPL;
//import model.Room;
//import java.util.UUID;
//
//public class AddRoomHandler implements CommandHandler {
//    private final RoomControllerIMPL roomController;
//
//    public AddRoomHandler(RoomControllerIMPL roomController) {
//        this.roomController = roomController;
//    }
//
//    @Override
//    public void handle(Scanner scanner) {
//        System.out.println("Enter room number:");
//        int roomNumber = scanner.nextInt();
//        System.out.println("Enter room price:");
//        double price = scanner.nextDouble();
//        System.out.println("Enter room capacity:");
//        int capacity = scanner.nextInt();
//        System.out.println("Enter room stars:");
//        int stars = scanner.nextInt();
//        UUID id = UUID.randomUUID();
//        String id1 = id.toString();
//
//        Room newRoom = new Room(id.toString(), roomNumber, "Available", price, capacity, stars);
//        roomController.addRoom(newRoom);
//        System.out.println("Room added successfully.");
//    }
//}











// package ui.handler;

// import java.util.Scanner;
// import controller.RoomControllerIMPL;
// import model.Room;
// import java.util.UUID;

// public class AddRoomHandler implements CommandHandler {

//     private final RoomControllerIMPL roomController;

//     public AddRoomHandler(RoomControllerIMPL roomController) {
//         this.roomController = roomController;
//     }

//     @Override
//     public void handle(Scanner scanner) {
//         System.out.println("Enter room number:");
//         int roomNumber = scanner.nextInt();
//         System.out.println("Enter room price:");
//         double price = scanner.nextDouble();
//         System.out.println("Enter room capacity:");
//         int capacity = scanner.nextInt();
//         System.out.println("Enter room stars:");
//         int stars = scanner.nextInt();

//         // Генерируем новый UUID для комнаты
//         String id = UUID.randomUUID().toString();

//         // Создаём объект Room
//         Room newRoom = new Room(id, roomNumber, "Available", price, capacity, stars);
//         roomController.addRoom(newRoom);
//         System.out.println("Room added successfully.");
//     }
// }


package com.test.ui.handler;

import java.util.Scanner;
import com.test.controller.RoomControllerIMPL;


public class AddRoomHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public AddRoomHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        System.out.println("Enter room price:");
        double price = scanner.nextDouble();
        System.out.println("Enter room capacity:");
        int capacity = scanner.nextInt();
        System.out.println("Enter room stars:");
        int stars = scanner.nextInt();

        // Добавляем комнату через RoomControllerIMPL
        roomController.addRoomToDatabase(roomNumber, price, capacity, stars);
        System.out.println("Room added successfully.");
    }
}
