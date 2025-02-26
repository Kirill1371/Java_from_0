package ru.senla.javacourse.tarasov.hotel.ui;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.ui.handler.CommandHandler;

public class MenuItem {
    private final String title; 
    private final CommandHandler handler;

    public MenuItem(String title, CommandHandler handler) {
        this.title = title;
        this.handler = handler;
    }

    public String getTitle() {
        return title;
    }

    public void execute(Scanner scanner) {
        handler.handle(scanner);
    }
}
