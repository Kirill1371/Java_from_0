import config.DependencyInjector;
import controller.CheckControllerIMPL;
import controller.GuestControllerIMPL;
import controller.RoomControllerIMPL;
import controller.ServiceControllerIMPL;
import model.Room;
import service.PersistenceService;
import ui.ConsoleUI;
import ui.MenuItem;
import ui.handler.*;

import resources.*;
import resources.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {

        DependencyInjector injector = new DependencyInjector(new String[] {"repository", "service", "controller", "ui.handler"});

        CheckControllerIMPL checkController = injector.getBean(CheckControllerIMPL.class);
        GuestControllerIMPL guestController = injector.getBean(GuestControllerIMPL.class);
        RoomControllerIMPL roomController = injector.getBean(RoomControllerIMPL.class);
        ServiceControllerIMPL serviceController = injector.getBean(ServiceControllerIMPL.class);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        AtomicReference<List<Room>> roomsRef = new AtomicReference<>(PersistenceService.loadState());
        if (roomsRef.get() == null) {
            roomsRef.set(new ArrayList<>());
        }

        try {
            System.out.println("Calling DatabaseConnection.getConnection()...");
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Add Room", new AddRoomHandler(roomController)));
        menuItems.add(new MenuItem("Remove Room", new RemoveRoomHandler(roomController)));
        menuItems.add(new MenuItem("Check In Guest", new CheckInHandler(checkController, dateFormat)));
        menuItems.add(new MenuItem("Check Out Guest", new CheckOutHandler(checkController)));
        menuItems.add(new MenuItem("Set Room Status", new SetRoomStatusHandler(roomController)));
        menuItems.add(new MenuItem("Set Room Price", new SetRoomPriceHandler(roomController)));
        menuItems.add(new MenuItem("Add Service", new AddServiceHandler(serviceController)));
        menuItems.add(new MenuItem("List All Rooms", new ListAllRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms", new ListAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List All Guests", new ListAllGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Price", new ListRoomsSortedByPriceHandler(roomController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Capacity", new ListRoomsSortedByCapacityHandler(roomController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Stars", new ListRoomsSortedByStarsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Price", new ListAvailableRoomsSortedByPriceHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Capacity", new ListAvailableRoomsSortedByCapacityHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Stars", new ListAvailableRoomsSortedByStarsHandler(roomController)));
        menuItems.add(new MenuItem("List Guests Sorted By Name", new ListGuestsSortedByNameHandler(guestController)));
        menuItems.add(new MenuItem("List Guests Sorted By Check Out Date", new ListGuestsSortedByCheckOutDateHandler(guestController)));
        menuItems.add(new MenuItem("Get Total Available Rooms", new GetTotalAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("Get Total Guests", new GetTotalGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Available By Date", new ListRoomsAvailableByDateHandler(roomController, dateFormat)));
        menuItems.add(new MenuItem("Get Total Payment For Guest", new GetTotalPaymentForGuestHandler(guestController)));
        menuItems.add(new MenuItem("List Last Three Stays", new ListLastThreeStaysHandler(roomController)));
        menuItems.add(new MenuItem("List Guest Services Sorted By Price", new ListGuestServicesSortedByPriceHandler(serviceController)));
        menuItems.add(new MenuItem("List Guest Services Sorted By Date", new ListGuestServicesSortedByDateHandler(serviceController)));
        menuItems.add(new MenuItem("Get Room Details", new GetRoomDetailsHandler(roomController)));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> PersistenceService.saveState(roomsRef.get())));

        ConsoleUI consoleUI = new ConsoleUI(menuItems);
        consoleUI.start();
    }
}
