package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import controller.HotelController;

public interface CommandHandler {
    boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat);
}