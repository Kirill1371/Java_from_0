package ui;

import ui.handler.CommandHandler;

import java.util.Scanner;

public class MenuItem {
    private final String title; // Название пункта меню
    private final CommandHandler handler; // Обработчик команды

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
