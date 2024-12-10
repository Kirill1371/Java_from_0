// package ui;

// import controller.HotelController;
// import model.Room;
// import model.Service;
// import model.Guest;
// import repository.HotelRepository;
// import service.HotelService;

// import java.util.Date;
// import java.util.Scanner;

// public class ConsoleUI {
//     private HotelController hotelController;

//     public ConsoleUI() {
//         HotelRepository hotelRepository = new HotelRepository();
//         HotelService hotelService = new HotelService(hotelRepository);
//         hotelController = new HotelController(hotelService);
//     }

//     public void start() {
//     Scanner scanner = new Scanner(System.in);
//     while (true) {
//         System.out.println("1. Add Room");
//         System.out.println("2. Remove Room");
//         System.out.println("3. Check In");
//         System.out.println("4. Check Out");
//         System.out.println("5. Set Room Status");
//         System.out.println("6. Set Room Price");
//         System.out.println("7. Add Service");
//         System.out.println("8. List All Rooms");
//         System.out.println("9. List Available Rooms");
//         System.out.println("10. List All Guests");
//         System.out.println("11. List Rooms Sorted By Price");
//         System.out.println("12. List Rooms Sorted By Capacity");
//         System.out.println("13. List Rooms Sorted By Stars");
//         System.out.println("14. List Available Rooms Sorted By Price");
//         System.out.println("15. List Available Rooms Sorted By Capacity");
//         System.out.println("16. List Available Rooms Sorted By Stars");
//         System.out.println("17. List Guests Sorted By Name");
//         System.out.println("18. List Guests Sorted By Check Out Date");
//         System.out.println("19. Get Total Available Rooms");
//         System.out.println("20. Get Total Guests");
//         System.out.println("21. List Rooms Available By Date");
//         System.out.println("22. Get Total Payment For Guest");
//         System.out.println("23. List Last Three Stays");
//         System.out.println("24. List Guest Services Sorted By Price");
//         System.out.println("25. List Guest Services Sorted By Date");
//         System.out.println("26. List Services Sorted By Category And Price");
//         System.out.println("27. Get Room Details");
//         System.out.println("28. Exit");

//         int choice = scanner.nextInt();
//         scanner.nextLine(); // consume newline

//         switch (choice) {
//             case 1:
//                 System.out.println("Enter room number:");
//                 int roomNumber = scanner.nextInt();
//                 System.out.println("Enter room price:");
//                 double price = scanner.nextDouble();
//                 System.out.println("Enter room capacity:");
//                 int capacity = scanner.nextInt();
//                 System.out.println("Enter room stars:");
//                 int stars = scanner.nextInt();
//                 hotelController.addRoom(new Room(roomNumber, price, capacity, stars));
//                 break;
//             case 2:
//                 System.out.println("Enter room number:");
//                 roomNumber = scanner.nextInt();
//                 hotelController.removeRoom(roomNumber);
//                 break;
//             case 3:
//                 System.out.println("Enter room number:");
//                 roomNumber = scanner.nextInt();
//                 scanner.nextLine(); // consume newline
//                 System.out.println("Enter guest name:");
//                 String guestName = scanner.nextLine();
//                 System.out.println("Enter check-in date (yyyy-MM-dd):");
//                 String checkInDateStr = scanner.nextLine();
//                 System.out.println("Enter check-out date (yyyy-MM-dd):");
//                 String checkOutDateStr = scanner.nextLine();
//                 Date checkInDate = new Date(checkInDateStr);
//                 Date checkOutDate = new Date(checkOutDateStr);
//                 Guest guest = new Guest(guestName);
//                 hotelController.checkIn(roomNumber, guest, checkInDate, checkOutDate);
//                 break;
//             case 4:
//                 System.out.println("Enter room number:");
//                 roomNumber = scanner.nextInt();
//                 hotelController.checkOut(roomNumber);
//                 break;
//             case 5:
//                 System.out.println("Enter room number:");
//                 roomNumber = scanner.nextInt();
//                 scanner.nextLine(); // consume newline
//                 System.out.println("Enter room status:");
//                 String status = scanner.nextLine();
//                 hotelController.setRoomStatus(roomNumber, status);
//                 break;
//             case 6:
//                 System.out.println("Enter room number:");
//                 roomNumber = scanner.nextInt();
//                 System.out.println("Enter room price:");
//                 price = scanner.nextDouble();
//                 hotelController.setRoomPrice(roomNumber, price);
//                 break;
//             case 7:
//                 System.out.println("Enter service name:");
//                 String serviceName = scanner.nextLine();
//                 System.out.println("Enter service price:");
//                 price = scanner.nextDouble();
//                 scanner.nextLine(); // consume newline
//                 System.out.println("Enter service category:");
//                 String category = scanner.nextLine();
//                 hotelController.addService(new Service(serviceName, price, category));
//                 break;
//             case 8:
//                 hotelController.listAllRooms();
//                 break;
//             case 9:
//                 hotelController.listAvailableRooms();
//                 break;
//             case 10:
//                 hotelController.listAllGuests();
//                 break;
//             case 11:
//                 hotelController.listRoomsSortedByPrice();
//                 break;
//             case 12:
//                 hotelController.listRoomsSortedByCapacity();
//                 break;
//             case 13:
//                 hotelController.listRoomsSortedByStars();
//                 break;
//             case 14:
//                 hotelController.listAvailableRoomsSortedByPrice();
//                 break;
//             case 15:
//                 hotelController.listAvailableRoomsSortedByCapacity();
//                 break;
//             case 16:
//                 hotelController.listAvailableRoomsSortedByStars();
//                 break;
//             case 17:
//                 hotelController.listGuestsSortedByName();
//                 break;
//             case 18:
//                 hotelController.listGuestsSortedByCheckOutDate();
//                 break;
//             case 19:
//                 hotelController.getTotalAvailableRooms();
//                 break;
//             case 20:
//                 hotelController.getTotalGuests();
//                 break;
//             case 21:
//                 System.out.println("Enter date (yyyy-MM-dd):");
//                 String dateStr = scanner.nextLine();
//                 Date date = new Date(dateStr);
//                 hotelController.listRoomsAvailableByDate(date);
//                 break;
//             case 22:
//                 System.out.println("Enter guest name:");
//                 guestName = scanner.nextLine();
//                 hotelController.getTotalPaymentForGuest(guestName);
//                 break;
//             case 23:
//                 System.out.println("Enter room number:");
//                 roomNumber = scanner.nextInt();
//                 hotelController.listLastThreeStays(roomNumber);
//                 break;
//                 case 24:
//                     System.out.println("Enter guest name:");
//                     guestName = scanner.nextLine();
//                     hotelController.listGuestServicesSortedByPrice(guestName);
//                     break;
//                 case 25:
//                     System.out.println("Enter guest name:");
//                     guestName = scanner.nextLine();
//                     hotelController.listGuestServicesSortedByDate(guestName);
//                     break;
//                 case 26:
//                     hotelController.listServicesSortedByCategoryAndPrice();
//                     break;
//                 case 27:
//                     System.out.println("Enter room number:");
//                     roomNumber = scanner.nextInt();
//                     hotelController.getRoomDetails(roomNumber);
//                     break;
//                 case 28:
//                     System.out.println("Exiting...");
//                     scanner.close();
//                     return;
//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//         }
//     }
// }



package ui;

import controller.HotelController;
import model.Room;
import model.Service;
import model.Guest;
import repository.HotelRepository;
import service.HotelService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleUI {
    private HotelController hotelController;

    public ConsoleUI() {
        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);
        hotelController = new HotelController(hotelService);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Check In");
            System.out.println("4. Check Out");
            System.out.println("5. Set Room Status");
            System.out.println("6. Set Room Price");
            System.out.println("7. Add Service");
            System.out.println("8. List All Rooms");
            System.out.println("9. List Available Rooms");
            System.out.println("10. List All Guests");
            System.out.println("11. List Rooms Sorted By Price");
            System.out.println("12. List Rooms Sorted By Capacity");
            System.out.println("13. List Rooms Sorted By Stars");
            System.out.println("14. List Available Rooms Sorted By Price");
            System.out.println("15. List Available Rooms Sorted By Capacity");
            System.out.println("16. List Available Rooms Sorted By Stars");
            System.out.println("17. List Guests Sorted By Name");
            System.out.println("18. List Guests Sorted By Check Out Date");
            System.out.println("19. Get Total Available Rooms");
            System.out.println("20. Get Total Guests");
            System.out.println("21. List Rooms Available By Date");
            System.out.println("22. Get Total Payment For Guest");
            System.out.println("23. List Last Three Stays");
            System.out.println("24. List Guest Services Sorted By Price");
            System.out.println("25. List Guest Services Sorted By Date");
            System.out.println("26. List Services Sorted By Category And Price");
            System.out.println("27. Get Room Details");
            System.out.println("28. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter room number:");
                    int roomNumber = scanner.nextInt();
                    System.out.println("Enter room price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter room capacity:");
                    int capacity = scanner.nextInt();
                    System.out.println("Enter room stars:");
                    int stars = scanner.nextInt();
                    hotelController.addRoom(new Room(roomNumber, price, capacity, stars));
                    break;
                case 2:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    hotelController.removeRoom(roomNumber);
                    break;
                case 3:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter guest name:");
                    String guestName = scanner.nextLine();
                    System.out.println("Enter check-in date (yyyy-MM-dd):");
                    String checkInDateStr = scanner.nextLine();
                    System.out.
                    println("Enter check-out date (yyyy-MM-dd):");
                    String checkOutDateStr = scanner.nextLine();
                    try {
                        Date checkInDate = dateFormat.parse(checkInDateStr);
                        Date checkOutDate = dateFormat.parse(checkOutDateStr);
                        Guest guest = new Guest(guestName);
                        hotelController.checkIn(roomNumber, guest, checkInDate, checkOutDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                    break;
                case 4:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    hotelController.checkOut(roomNumber);
                    break;
                case 5:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter room status:");
                    String status = scanner.nextLine();
                    hotelController.setRoomStatus(roomNumber, status);
                    break;
                case 6:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    System.out.println("Enter room price:");
                    price = scanner.nextDouble();
                    hotelController.setRoomPrice(roomNumber, price);
                    break;
                case 7:
                    System.out.println("Enter service name:");
                    String serviceName = scanner.nextLine();
                    System.out.println("Enter service price:");
                    price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter service category:");
                    String category = scanner.nextLine();
                    hotelController.addService(new Service(serviceName, price, category));
                    break;
                case 8:
                    hotelController.listAllRooms();
                    break;
                case 9:
                    hotelController.listAvailableRooms();
                    break;
                case 10:
                    hotelController.listAllGuests();
                    break;
                case 11:
                    hotelController.listRoomsSortedByPrice();
                    break;
                case 12:
                    hotelController.listRoomsSortedByCapacity();
                    break;
                case 13:
                    hotelController.listRoomsSortedByStars();
                    break;
                case 14:
                    hotelController.listAvailableRoomsSortedByPrice();
                    break;
                case 15:
                    hotelController.listAvailableRoomsSortedByCapacity();
                    break;
                case 16:
                    hotelController.listAvailableRoomsSortedByStars();
                    break;
                case 17:
                    hotelController.listGuestsSortedByName();
                    break;
                case 18:
                    hotelController.listGuestsSortedByCheckOutDate();
                    break;
                case 19:
                    hotelController.getTotalAvailableRooms();
                    break;
                case 20:
                    hotelController.getTotalGuests();
                    break;
                case 21:
                    System.out.println("Enter date (yyyy-MM-dd):");
                    String dateStr = scanner.nextLine();
                    try {
                        Date date = dateFormat.parse(dateStr);
                        hotelController.listRoomsAvailableByDate(date);
                    } catch (ParseException e) {
                        System.out.
                        println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                    break;
                case 22:
                    System.out.println("Enter guest name:");
                    guestName = scanner.nextLine();
                    hotelController.getTotalPaymentForGuest(guestName);
                    break;
                case 23:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    hotelController.listLastThreeStays(roomNumber);
                    break;
                case 24:
                    System.out.println("Enter guest name:");
                    guestName = scanner.nextLine();
                    hotelController.listGuestServicesSortedByPrice(guestName);
                    break;
                case 25:
                    System.out.println("Enter guest name:");
                    guestName = scanner.nextLine();
                    hotelController.listGuestServicesSortedByDate(guestName);
                    break;
                case 26:
                    hotelController.listServicesSortedByCategoryAndPrice();
                    break;
                case 27:
                    System.out.println("Enter room number:");
                    roomNumber = scanner.nextInt();
                    hotelController.getRoomDetails(roomNumber);
                    break;
                case 28:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
