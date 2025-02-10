package com.test;

import com.test.config.DependencyInjector;
import com.test.controller.CheckControllerIMPL;
import com.test.controller.GuestControllerIMPL;
import com.test.controller.RoomControllerIMPL;
import com.test.controller.ServiceControllerIMPL;
import com.test.model.Room;
import com.test.service.PersistenceService;
import com.test.ui.ConsoleUI;
import com.test.ui.MenuItem;
import com.test.ui.handler.*;

import com.test.resources.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {

        DependencyInjector injector = new DependencyInjector("com.test.resources.database", "com.test.repository", "com.test.service", "com.test.controller", "com.test.ui.handler");

        DatabaseConnection databaseConnection = injector.getBean(DatabaseConnection.class);
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
            logger.info("Calling DatabaseConnection.getConnection()...");
            Connection connection = databaseConnection.getConnection();
            logger.info("Connection successful!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Failed to connect to the database: " + e.getMessage());
        }

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Add Room", new com.test.ui.handler.AddRoomHandler(roomController)));
        menuItems.add(new MenuItem("Remove Room", new com.test.ui.handler.RemoveRoomHandler(roomController)));
        menuItems.add(new MenuItem("Check In Guest", new com.test.ui.handler.CheckInHandler(checkController, dateFormat)));
        menuItems.add(new MenuItem("Check Out Guest", new com.test.ui.handler.CheckOutHandler(checkController)));
        menuItems.add(new MenuItem("Set Room Status", new com.test.ui.handler.SetRoomStatusHandler(roomController)));
        menuItems.add(new MenuItem("Set Room Price", new com.test.ui.handler.SetRoomPriceHandler(roomController)));
        menuItems.add(new MenuItem("Add Service", new com.test.ui.handler.AddServiceHandler(serviceController)));
        menuItems.add(new MenuItem("List All Rooms", new com.test.ui.handler.ListAllRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms", new com.test.ui.handler.ListAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List All Guests", new com.test.ui.handler.ListAllGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Capacity", new com.test.ui.handler.ListRoomsSortedByCapacityHandler(roomController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Stars", new com.test.ui.handler.ListRoomsSortedByStarsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Price", new com.test.ui.handler.ListAvailableRoomsSortedByPriceHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Capacity", new com.test.ui.handler.ListAvailableRoomsSortedByCapacityHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Stars", new com.test.ui.handler.ListAvailableRoomsSortedByStarsHandler(roomController)));
        menuItems.add(new MenuItem("List Guests Sorted By Name", new com.test.ui.handler.ListGuestsSortedByNameHandler(guestController)));
        menuItems.add(new MenuItem("List Guests Sorted By Check Out Date", new com.test.ui.handler.ListGuestsSortedByCheckOutDateHandler(guestController)));
        menuItems.add(new MenuItem("Get Total Available Rooms", new com.test.ui.handler.GetTotalAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("Get Total Guests", new com.test.ui.handler.GetTotalGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Available By Date", new com.test.ui.handler.ListRoomsAvailableByDateHandler(roomController, dateFormat)));
        menuItems.add(new MenuItem("Get Total Payment For Guest", new com.test.ui.handler.GetTotalPaymentForGuestHandler(guestController)));
        menuItems.add(new MenuItem("List Last Three Stays", new com.test.ui.handler.ListLastThreeStaysHandler(roomController)));
        menuItems.add(new MenuItem("List Guest Services Sorted By Price", new com.test.ui.handler.ListGuestServicesSortedByPriceHandler(serviceController)));
        menuItems.add(new MenuItem("List Guest Services Sorted By Date", new com.test.ui.handler.ListGuestServicesSortedByDateHandler(serviceController)));
        menuItems.add(new MenuItem("Get Room Details", new com.test.ui.handler.GetRoomDetailsHandler(roomController)));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> PersistenceService.saveState(roomsRef.get())));

        ConsoleUI consoleUI = new ConsoleUI(menuItems);
        consoleUI.start();

    }
}



