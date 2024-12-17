package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import controller.HotelController;
import repository.HotelRepository;
import service.HotelService;
import ui.ConsoleUI;
import ui.handler.AddRoomHandler;
import ui.handler.AddServiceHandler;
import ui.handler.CheckInHandler;
import ui.handler.CheckOutHandler;
import ui.handler.CommandHandler;
import ui.handler.ExitHandler;
import ui.handler.ExportRoomsHandler;
import ui.handler.GetRoomDetailsHandler;
import ui.handler.GetTotalAvailableRoomsHandler;
import ui.handler.GetTotalGuestsHandler;
import ui.handler.GetTotalPaymentForGuestHandler;
import ui.handler.ImportRoomsHandler;
import ui.handler.ListAllGuestsHandler;
import ui.handler.ListAllRoomsHandler;
import ui.handler.ListAvailableRoomsHandler;
import ui.handler.ListAvailableRoomsSortedByCapacityHandler;
import ui.handler.ListAvailableRoomsSortedByPriceHandler;
import ui.handler.ListAvailableRoomsSortedByStarsHandler;
import ui.handler.ListGuestServicesSortedByPriceHandler;
import ui.handler.ListGuestsSortedByCheckOutDateHandler;
import ui.handler.ListGuestsSortedByNameHandler;
import ui.handler.ListLastThreeStaysHandler;
import ui.handler.ListRoomsAvailableByDateHandler;
import ui.handler.ListRoomsSortedByCapacityHandler;
import ui.handler.ListRoomsSortedByStarsHandler;
import ui.handler.ListServicesSortedByCategoryAndPriceHandler;
import ui.handler.RemoveRoomHandler;
import ui.handler.SetRoomPriceHandler;
import ui.handler.SetRoomStatusHandler;
import ui.handler.listRoomsSortedByPriceHandler;

public class HotelManagementTest {

    public static void main(String[] args) {

        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);
        HotelController hotelController = new HotelController(hotelService);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new AddRoomHandler());
        handlers.add(new RemoveRoomHandler());
        handlers.add(new CheckInHandler());
        handlers.add(new CheckOutHandler());
        handlers.add(new SetRoomStatusHandler());
        handlers.add(new SetRoomPriceHandler());
        handlers.add(new AddServiceHandler());
        handlers.add(new ListAllRoomsHandler());
        handlers.add(new ListAvailableRoomsHandler());
        handlers.add(new ListAllGuestsHandler());
        handlers.add(new listRoomsSortedByPriceHandler());
        handlers.add(new ListRoomsSortedByCapacityHandler());
        handlers.add(new ListRoomsSortedByStarsHandler());
        handlers.add(new ListAvailableRoomsSortedByPriceHandler());
        handlers.add(new ListAvailableRoomsSortedByCapacityHandler());
        handlers.add(new ListAvailableRoomsSortedByStarsHandler());
        handlers.add(new ListGuestsSortedByNameHandler());
        handlers.add(new ListGuestsSortedByCheckOutDateHandler());
        handlers.add(new GetTotalAvailableRoomsHandler());
        handlers.add(new GetTotalGuestsHandler());
        handlers.add(new ListRoomsAvailableByDateHandler());
        handlers.add(new GetTotalPaymentForGuestHandler());
        handlers.add(new ListLastThreeStaysHandler());
        handlers.add(new ListGuestServicesSortedByPriceHandler());
        handlers.add(new ListGuestsSortedByCheckOutDateHandler());
        handlers.add(new ListServicesSortedByCategoryAndPriceHandler());
        handlers.add(new GetRoomDetailsHandler());
        handlers.add(new ExitHandler());
        handlers.add(new ImportRoomsHandler());
        handlers.add(new ExportRoomsHandler());


        //String filePath = "X:/SENLA/rooms.csv";

        ConsoleUI consoleUI = new ConsoleUI(handlers, hotelController, dateFormat);
        consoleUI.start();
    }
}
