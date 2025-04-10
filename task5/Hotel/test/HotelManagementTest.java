package test;

import ui.ConsoleUI;
import ui.MenuItem;
import ui.handler.*;

import controller.*;
import repository.HotelRepository;
import service.HotelService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HotelManagementTest {
    public static void main(String[] args) {
        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);
        CheckControllerIMPL checkController = new CheckControllerIMPL(hotelService);
        GuestControllerIMPL guestController = new GuestControllerIMPL(hotelService);
        RoomControllerIMPL roomController = new RoomControllerIMPL(hotelService);
        ServiceControllerIMPL serviceController = new ServiceControllerIMPL(hotelService);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Add Room", new AddRoomHandler(roomController)));
        menuItems.add(new MenuItem("Remove Room", new RemoveRoomHandler(roomController)));
        menuItems.add(new MenuItem("Check In Guest", new CheckInHandler(checkController, dateFormat)));
        menuItems.add(new MenuItem("Check Out Guest", new CheckOutHandler(checkController)));
        menuItems.add(new MenuItem("Set Room status", new SetRoomStatusHandler(roomController)));
        menuItems.add(new MenuItem("Set Room price", new SetRoomPriceHandler(roomController)));
        menuItems.add(new MenuItem("Add Service", new AddServiceHandler(serviceController)));
        menuItems.add(new MenuItem("List All Rooms", new ListAllRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms", new ListAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List All guests", new ListAllGuestsHandler(guestController)));
        menuItems.add(new MenuItem("List Rooms Sorted By Price", new listRoomsSortedByPriceHandler(roomController)));
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
        menuItems.add(new MenuItem("List Services Sorted By Category And Price", new ListServicesSortedByCategoryAndPriceHandler(serviceController)));
        menuItems.add(new MenuItem("Get Room Details", new GetRoomDetailsHandler(roomController)));

        ConsoleUI consoleUI = new ConsoleUI(menuItems);
        consoleUI.start();
    }
}
 