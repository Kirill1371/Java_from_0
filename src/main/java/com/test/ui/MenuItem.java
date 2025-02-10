package com.test.ui;

import com.test.ui.handler.CommandHandler;

import java.util.Scanner;

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
