// package ui;

// //import controller.HotelControllerImpl;
// import ui.handler.CommandHandler;

// import java.text.SimpleDateFormat;
// import java.util.List;
// import java.util.Scanner;

// import controller.CheckControllerIMPL;
// import controller.GuestControllerIMPL;
// import controller.RoomControllerIMPL;
// import controller.ServiceControllerIMPL;

// public class ConsoleUI {
//     private final List<CommandHandler> handlers;
//    // private final HotelControllerImpl hotelController;
//     private final CheckControllerIMPL checkController;
//     private final GuestControllerIMPL guestController;
//     private final RoomControllerIMPL roomController;
//     private final ServiceControllerIMPL serviceController;
//     private final SimpleDateFormat dateFormat;

//     // public ConsoleUI(List<CommandHandler> handlers, HotelControllerImpl hotelController, SimpleDateFormat dateFormat) {
//     public ConsoleUI(
//         List<CommandHandler> handlers, 
//         CheckControllerIMPL checkController,
//         GuestControllerIMPL guestController,
//         RoomControllerIMPL roomController,
//         ServiceControllerIMPL serviceController, 
//         SimpleDateFormat dateFormat
//     ) {

//         this.handlers = handlers;
//         this.checkController = checkController;
//         this.guestController = guestController;
//         this.roomController = roomController;
//         this.serviceController = serviceController;
//     // this.hotelController = hotelController;
//         this.dateFormat = dateFormat;
//     }

//     public void start() {
//         Scanner scanner = new Scanner(System.in);

//         while (true) {
//             System.out.println("1. Add Room");
//             System.out.println("2. Remove Room");
//             System.out.println("3. Check In");
//             System.out.println("4. Check Out");
//             System.out.println("5. Set Room Status");
//             System.out.println("6. Set Room Price");
//             System.out.println("7. Add Service");
//             System.out.println("8. List All Rooms");
//             System.out.println("9. List Available Rooms");
//             System.out.println("10. List All Guests");
//             System.out.println("11. List Rooms Sorted By Price");
//             System.out.println("12. List Rooms Sorted By Capacity");
//             System.out.println("13. List Rooms Sorted By Stars");
//             System.out.println("14. List Available Rooms Sorted By Price");
//             System.out.println("15. List Available Rooms Sorted By Capacity");
//             System.out.println("16. List Available Rooms Sorted By Stars");
//             System.out.println("17. List Guests Sorted By Name");
//             System.out.println("18. List Guests Sorted By Check Out Date");
//             System.out.println("19. Get Total Available Rooms");
//             System.out.println("20. Get Total Guests");
//             System.out.println("21. List Rooms Available By Date");
//             System.out.println("22. Get Total Payment For Guest");
//             System.out.println("23. List Last Three Stays");
//             System.out.println("24. List Guest Services Sorted By Price");
//             System.out.println("25. List Guest Services Sorted By Date");
//             System.out.println("26. List Services Sorted By Category And Price");
//             System.out.println("27. Get Room Details");
//             System.out.println("28. Exit");
//             System.out.println("29. Import");
//             System.out.println("30. Export");

//             int choice = scanner.nextInt();
//             scanner.nextLine(); // consume newline

//             boolean handled = false;
//             for (CommandHandler handler : handlers) {
//                 //if (handler.handle(choice, scanner, hotelController, dateFormat)) {
//                 if (handler.handle(
//                     choice, 
//                     scanner, 
//                     checkController, 
//                     guestController, 
//                     roomController, 
//                     serviceController, 
//                     dateFormat)) {
//                     handled = true;
//                     break;
//                 }
//             }

//             if (!handled) {
//                 System.out.println("Invalid choice.");
//             }

//             if (choice == 28) {
//                 System.out.println("Exiting...");
//                 return;
//             }
//         }
//     }
// }


package ui;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final List<MenuItem> menuItems;

    public ConsoleUI(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i).getTitle());
            }
            System.out.println((menuItems.size() + 1) + ". Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == menuItems.size() + 1) {
                System.out.println("Exiting...");
                return;
            }

            if (choice > 0 && choice <= menuItems.size()) {
                menuItems.get(choice - 1).execute(scanner);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
