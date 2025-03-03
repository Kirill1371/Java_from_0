package ru.senla.javacourse.tarasov.hotel.application;














import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.senla.javacourse.tarasov.hotel.impl.controller.CheckControllerImpl;
import ru.senla.javacourse.tarasov.hotel.impl.controller.GuestControllerImpl;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;
import ru.senla.javacourse.tarasov.hotel.impl.controller.ServiceControllerImpl;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepositoryImpl;
import ru.senla.javacourse.tarasov.hotel.db.entity.database.DatabaseConnection;
import ru.senla.javacourse.tarasov.hotel.db.entity.database.HibernateUtil;
import ru.senla.javacourse.tarasov.hotel.impl.service.PersistenceService;
//import ru.senla.javacourse.tarasov.hotel.impl.ui.ConsoleUI;
import ru.senla.javacourse.tarasov.hotel.ui.ConsoleUI;
import ru.senla.javacourse.tarasov.hotel.ui.MenuItem;
import ru.senla.javacourse.tarasov.hotel.ioc.DependencyInjector;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {

        DependencyInjector
                injector = new DependencyInjector("ru.senla.javacourse.tarasov.hotel.db.entity.database", "ru.senla.javacourse.tarasov.hotel.db.entity.model", "ru.senla.javacourse.tarasov.hotel.impl.repository", "ru.senla.javacourse.tarasov.hotel.impl.service", "ru.senla.javacourse.tarasov.hotel.impl.controller", "ru.senla.javacourse.tarasov.hotel.ui.handler");
                //injector = new DependencyInjector("ru.senla.javacourse.tarasov.hotel.impl.resources.database, ru.senla.javacourse.tarasov.hotel.impl.model, ru.senla.javacourse.tarasov.hotel.impl.repository, ru.senla.javacourse.tarasov.hotel.impl.service, ru.senla.javacourse.tarasov.hotel.impl.controller, ru.senla.javacourse.tarasov.hotel.impl.ui.handler");

        HibernateUtil hibernateUtil = injector.getBean(HibernateUtil.class);
        DatabaseConnection databaseConnection = injector.getBean(DatabaseConnection.class);
        HotelRepositoryImpl hotelRepository = injector.getBean(HotelRepositoryImpl.class);
        CheckControllerImpl checkController = injector.getBean(CheckControllerImpl.class);
        GuestControllerImpl guestController = injector.getBean(GuestControllerImpl.class);
        RoomControllerImpl roomController = injector.getBean(RoomControllerImpl.class);
        ServiceControllerImpl serviceController = injector.getBean(ServiceControllerImpl.class);

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
        menuItems.add(new MenuItem("Add Room", new ru.senla.javacourse.tarasov.hotel.ui.handler.AddRoomHandler(roomController)));
        menuItems.add(new MenuItem("Remove Room", new ru.senla.javacourse.tarasov.hotel.ui.handler.RemoveRoomHandler(roomController)));
        //menuItems.add(new MenuItem("Check In Guest", new ru.senla.javacourse.tarasov.hotel.ui.handler.CheckInHandler(checkController, dateFormat)));
        menuItems.add(new MenuItem("Check Out Guest", new ru.senla.javacourse.tarasov.hotel.ui.handler.CheckOutHandler(checkController)));
        menuItems.add(new MenuItem("Set Room Status", new ru.senla.javacourse.tarasov.hotel.ui.handler.SetRoomStatusHandler(roomController)));
        menuItems.add(new MenuItem("Set Room Price", new ru.senla.javacourse.tarasov.hotel.ui.handler.SetRoomPriceHandler(roomController)));
        //menuItems.add(new MenuItem("Add Service", new ru.senla.javacourse.tarasov.hotel.ui.handler.AddServiceHandler(serviceController)));
        menuItems.add(new MenuItem("List All Rooms", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListAllRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List All Guests", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListAllGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Capacity", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListRoomsSortedByCapacityHandler(roomController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Stars", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListRoomsSortedByStarsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Price", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsSortedByPriceHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Capacity", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsSortedByCapacityHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms Sorted By Stars", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsSortedByStarsHandler(roomController)));
        menuItems.add(new MenuItem("List Guests Sorted By Name", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestsSortedByNameHandler(guestController)));
        menuItems.add(new MenuItem("List Guests Sorted By Check Out Date", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestsSortedByCheckOutDateHandler(guestController)));
        menuItems.add(new MenuItem("Get Total Available Rooms", new ru.senla.javacourse.tarasov.hotel.ui.handler.GetTotalAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("Get Total Guests", new ru.senla.javacourse.tarasov.hotel.ui.handler.GetTotalGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Available By Date", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListRoomsAvailableByDateHandler(roomController, dateFormat)));
        menuItems.add(new MenuItem("Get Total Payment For Guest", new ru.senla.javacourse.tarasov.hotel.ui.handler.GetTotalPaymentForGuestHandler(guestController)));
        menuItems.add(new MenuItem("List Last Three Stays", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListLastThreeStaysHandler(roomController)));
        menuItems.add(new MenuItem("List Guest Services Sorted By Price", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestServicesSortedByPriceHandler(serviceController)));
        menuItems.add(new MenuItem("List Guest Services Sorted By Date", new ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestServicesSortedByDateHandler(serviceController)));
        menuItems.add(new MenuItem("Get Room Details", new ru.senla.javacourse.tarasov.hotel.ui.handler.GetRoomDetailsHandler(roomController)));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> PersistenceService.saveState(roomsRef.get())));

        ConsoleUI consoleUI = new ConsoleUI(menuItems);
        consoleUI.start();

    }
}



