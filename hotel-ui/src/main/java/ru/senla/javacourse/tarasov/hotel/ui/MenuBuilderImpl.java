package ru.senla.javacourse.tarasov.hotel.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;
import ru.senla.javacourse.tarasov.hotel.ui.handler.AddRoomHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.CheckOutHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.GetRoomDetailsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.GetTotalAvailableRoomsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.GetTotalGuestsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.GetTotalPaymentForGuestHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListAllGuestsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListAllRoomsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsSortedByCapacityHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsSortedByPriceHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListAvailableRoomsSortedByStarsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestServicesSortedByDateHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestServicesSortedByPriceHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestsSortedByCheckOutDateHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListGuestsSortedByNameHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListLastThreeStaysHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListRoomsAvailableByDateHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListRoomsSortedByCapacityHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.ListRoomsSortedByStarsHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.RemoveRoomHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.SetRoomPriceHandler;
import ru.senla.javacourse.tarasov.hotel.ui.handler.SetRoomStatusHandler;

@Component
public class MenuBuilderImpl implements MenuBuilder {

    @Inject
    private RoomController roomController;
    @Inject
    private CheckController checkController;
    @Inject
    private GuestController guestController;
    @Inject
    private ServiceController serviceController;

    @Override
    public List<MenuItem> buildMenuItems() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Add Room", new AddRoomHandler(roomController)));
        menuItems.add(new MenuItem("Remove Room", new RemoveRoomHandler(roomController)));
        menuItems.add(new MenuItem("Check Out Guest", new CheckOutHandler(checkController)));
        menuItems.add(new MenuItem("Set Room Status", new SetRoomStatusHandler(roomController)));
        menuItems.add(new MenuItem("Set Room Price", new SetRoomPriceHandler(roomController)));
        menuItems.add(new MenuItem("List All Rooms", new ListAllRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List Available Rooms", new ListAvailableRoomsHandler(roomController)));
        menuItems.add(new MenuItem("List All Guests", new ListAllGuestsHandler(guestController)));
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
        return menuItems;
    }
}
